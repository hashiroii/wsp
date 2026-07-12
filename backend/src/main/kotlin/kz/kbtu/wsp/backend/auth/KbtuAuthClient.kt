package kz.kbtu.wsp.backend.auth

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kz.kbtu.wsp.backend.config.KbtuConfig

class KbtuAuthClient {

    private val http = HttpClient(CIO) {
        followRedirects = false
        install(HttpTimeout) { requestTimeoutMillis = 15_000 }
    }

    suspend fun login(username: String, password: String): String {
        val initResponse = http.get(KbtuConfig.BASE_URL) {
            header(HttpHeaders.UserAgent, KbtuConfig.USER_AGENT)
        }
        val initCookies = initResponse.setCookies()

        val loginResponse = http.post(KbtuConfig.LOGIN_URL) {
            header(HttpHeaders.UserAgent, KbtuConfig.USER_AGENT)
            if (initCookies.isNotEmpty()) header(HttpHeaders.Cookie, initCookies)
            setBody(FormDataContent(Parameters.build {
                append(KbtuConfig.FIELD_USERNAME, username)
                append(KbtuConfig.FIELD_PASSWORD, password)
            }))
        }

        val sessionCookie = loginResponse.headers.getAll(HttpHeaders.SetCookie)
            ?.firstOrNull { it.startsWith("JSESSIONID") || it.startsWith("SESSION") }
            ?.substringBefore(";")
            ?: throw AuthException(
                "Login failed — no session cookie in response. " +
                "Verify LOGIN_URL and field names in KbtuConfig, " +
                "and check if the cookie name is different (DevTools → Response Headers → Set-Cookie)."
            )

        return sessionCookie
    }
}

private fun HttpResponse.setCookies(): String =
    headers.getAll(HttpHeaders.SetCookie)
        ?.joinToString("; ") { it.substringBefore(";") }
        ?: ""

class AuthException(message: String) : Exception(message)
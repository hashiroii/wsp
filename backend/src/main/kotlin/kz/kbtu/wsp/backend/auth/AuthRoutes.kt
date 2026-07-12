package kz.kbtu.wsp.backend.auth

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kz.kbtu.wsp.backend.model.ErrorResponse
import kz.kbtu.wsp.backend.model.LoginRequest
import kz.kbtu.wsp.backend.model.LoginResponse
import java.util.UUID

fun Application.authRoutes(kbtuAuth: KbtuAuthClient, sessions: SessionStore) {
    routing {
        route("/api/v1/auth") {

            // POST /api/v1/auth/login
            // Body: { "username": "...", "password": "..." }
            // Response 200: { "token": "<uuid>" }
            // Response 401: { "message": "..." }
            post("/login") {
                val body = call.receive<LoginRequest>()
                try {
                    val kbtuCookie = kbtuAuth.login(body.username, body.password)
                    val token = UUID.randomUUID().toString()
                    sessions.save(token, kbtuCookie)
                    call.respond(HttpStatusCode.OK, LoginResponse(token = token))
                } catch (e: AuthException) {
                    call.respond(HttpStatusCode.Unauthorized, ErrorResponse(e.message ?: "Login failed"))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, ErrorResponse("Unexpected error: ${e.message}"))
                }
            }

            // POST /api/v1/auth/logout
            // Header: Authorization: Bearer <token>
            post("/logout") {
                val token = call.bearerToken()
                if (token != null) sessions.delete(token)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}

fun ApplicationCall.bearerToken(): String? =
    request.headers[HttpHeaders.Authorization]?.removePrefix("Bearer ")?.trim()
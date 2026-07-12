package kz.kbtu.wsp.backend

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kz.kbtu.wsp.backend.auth.KbtuAuthClient
import kz.kbtu.wsp.backend.auth.SessionStore
import kz.kbtu.wsp.backend.auth.authRoutes
import kz.kbtu.wsp.backend.plugins.configureCORS
import kz.kbtu.wsp.backend.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080) {
        val sessionStore = SessionStore()
        val kbtuAuthClient = KbtuAuthClient()

        configureSerialization()
        configureCORS()
        authRoutes(kbtuAuthClient, sessionStore)
    }.start(wait = true)
}
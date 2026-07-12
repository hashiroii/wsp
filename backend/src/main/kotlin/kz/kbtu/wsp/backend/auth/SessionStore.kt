package kz.kbtu.wsp.backend.auth

import java.util.concurrent.ConcurrentHashMap

class SessionStore {
    private val sessions = ConcurrentHashMap<String, String>()

    fun save(token: String, kbtuCookie: String) {
        sessions[token] = kbtuCookie
    }

    fun get(token: String): String? = sessions[token]

    fun delete(token: String) {
        sessions.remove(token)
    }
}
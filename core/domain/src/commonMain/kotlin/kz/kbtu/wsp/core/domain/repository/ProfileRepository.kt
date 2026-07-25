package kz.kbtu.wsp.core.domain.repository

import kz.kbtu.wsp.core.domain.model.ProfileData

interface ProfileRepository {
    suspend fun getProfile(): ProfileData
}
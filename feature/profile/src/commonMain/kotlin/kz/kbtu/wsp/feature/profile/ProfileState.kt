package kz.kbtu.wsp.feature.profile

import kz.kbtu.wsp.core.domain.model.ProfileData

data class ProfileState(
    val profile: ProfileData = ProfileData(),
    val showPhotoPreview: Boolean = false,
    val activeSection: ProfileSection = ProfileSection.MainInfo
)
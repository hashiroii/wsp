package kz.kbtu.wsp.feature.profile

data class ProfileState(
    val profile: ProfileData = ProfileData(),
    val showPhotoPreview: Boolean = false,
    val activeSection: ProfileSection = ProfileSection.MainInfo
)
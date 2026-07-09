package kz.kbtu.wsp.feature.profile

data class ProfileState(
    val profile: ProfileData = ProfileData(),
    val isLoading: Boolean = false,
    val showPhotoPreview: Boolean = false
)
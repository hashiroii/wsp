package kz.kbtu.wsp.feature.profile

sealed class ProfileIntent {
    data object ShowPhotoPreview : ProfileIntent()
    data object DismissPhotoPreview : ProfileIntent()
    data class NavigateToSection(val section: ProfileSection) : ProfileIntent()
}
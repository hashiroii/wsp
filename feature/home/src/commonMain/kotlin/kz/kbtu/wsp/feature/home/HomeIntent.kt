package kz.kbtu.wsp.feature.home

sealed class HomeIntent {
    data class OnNewsItemClick(val newsId: String) : HomeIntent()
    data object OnViewAllNewsClick : HomeIntent()
    data object OnAttendanceClick : HomeIntent()
    data object OnAttestationClick : HomeIntent()
    data object OnTranscriptClick : HomeIntent()
    data object OnFxRegistrationClick : HomeIntent()
    data object OnCourseRegistrationClick : HomeIntent()
    data object OnAddDropClick : HomeIntent()
    data object OnMapClick : HomeIntent()
    data object OnLostFoundClick : HomeIntent()
    data object OnFinancialClick : HomeIntent()
}
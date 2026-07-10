package kz.kbtu.wsp.feature.home

data class HomeState(
    val gpa: String = "—",
    val isAttendanceActive: Boolean = false,
    val isLoading: Boolean = false,
    val currentWeek: Int = 0
)
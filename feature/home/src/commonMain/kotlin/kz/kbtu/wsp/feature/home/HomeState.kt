package kz.kbtu.wsp.feature.home

import kz.kbtu.wsp.core.domain.model.NewsItem

data class HomeState(
    val gpa: String = "—",
    val isAttendanceActive: Boolean = false,
    val isLoading: Boolean = false,
    val currentWeek: Int = 0,
    val pinnedNews: List<NewsItem> = emptyList()
)
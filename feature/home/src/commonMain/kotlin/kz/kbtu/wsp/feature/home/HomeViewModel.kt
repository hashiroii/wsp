package kz.kbtu.wsp.feature.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.kbtu.wsp.core.domain.model.NewsItem

private val MOCK_PINNED: List<NewsItem> = listOf(
    NewsItem(
        "n1", "KBTU Named Top University in Kazakhstan for 2025–2026",
        "According to the QS World University Rankings 2025–2026, KBTU has secured the top position among Kazakhstani universities for the third consecutive year.",
        "01.07.2026", 23, isPinned = true
    ),
    NewsItem(
        "n2", "Fall Semester 2026–2027 Registration Now Open",
        "The Academic Affairs Office announces that course registration for the upcoming Fall semester is now available in the student portal.",
        "25.06.2026", 11, isPinned = true
    ),
    NewsItem(
        "n3", "New Student Center Opening Ceremony — August 15",
        "After two years of construction, the KBTU Student Center is set to open its doors on August 15, 2026.",
        "20.06.2026", 34, isPinned = true
    )
)

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        HomeState(currentWeek = currentWeekOfYear(), pinnedNews = MOCK_PINNED)
    )
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            else -> Unit
        }
    }
}
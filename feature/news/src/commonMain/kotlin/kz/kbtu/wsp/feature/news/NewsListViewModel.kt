package kz.kbtu.wsp.feature.news

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewsListViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        NewsListState(
            pinnedNews = MOCK_PINNED_NEWS,
            pagedNews = MOCK_PAGED_NEWS
        )
    )
    val state: StateFlow<NewsListState> = _state.asStateFlow()

    fun onIntent(intent: NewsListIntent) {
        when (intent) {
            is NewsListIntent.OnSearchQueryChange ->
                _state.update { it.copy(searchQuery = intent.query) }
            is NewsListIntent.OnNewsClick,
            is NewsListIntent.OnNewsCommentClick -> Unit
        }
    }
}
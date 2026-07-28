package kz.kbtu.wsp.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.kbtu.wsp.core.domain.repository.NewsRepository

class NewsListViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NewsListState())
    val state: StateFlow<NewsListState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val allNews = newsRepository.getAllNews()
            _state.update {
                it.copy(
                    pinnedNews = allNews.filter { news -> news.isPinned },
                    pagedNews = allNews.filterNot { news -> news.isPinned }
                )
            }
        }
    }

    fun onIntent(intent: NewsListIntent) {
        when (intent) {
            is NewsListIntent.OnSearchQueryChange ->
                _state.update { it.copy(searchQuery = intent.query) }
            is NewsListIntent.OnNewsClick,
            is NewsListIntent.OnNewsCommentClick -> Unit
        }
    }
}
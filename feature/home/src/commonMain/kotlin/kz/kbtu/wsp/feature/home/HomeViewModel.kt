package kz.kbtu.wsp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.kbtu.wsp.core.domain.repository.NewsRepository

class HomeViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState(currentWeek = currentWeekOfYear()))
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val pinnedNews = newsRepository.getAllNews().filter { it.isPinned }
            _state.update { it.copy(pinnedNews = pinnedNews) }
        }
    }

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            else -> Unit
        }
    }
}
package kz.kbtu.wsp.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kz.kbtu.wsp.core.domain.model.NewsComment
import kz.kbtu.wsp.core.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsDetailViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NewsDetailState())
    val state: StateFlow<NewsDetailState> = _state.asStateFlow()

    private var commentCounter = 0

    fun load(newsId: String, openKeyboard: Boolean) {
        viewModelScope.launch {
            val news = newsRepository.getAllNews().find { it.id == newsId }
            val comments = newsRepository.getComments(newsId)
            _state.update {
                it.copy(
                    news = news,
                    comments = comments,
                    openKeyboard = openKeyboard
                )
            }
        }
    }

    fun onIntent(intent: NewsDetailIntent) {
        when (intent) {
            is NewsDetailIntent.OnCommentInputChange ->
                _state.update { it.copy(commentInput = intent.text) }

            NewsDetailIntent.SendComment -> {
                val text = _state.value.commentInput.trim()
                if (text.isNotEmpty()) {
                    val comment = NewsComment(
                        id = "user_${++commentCounter}",
                        authorName = "Student U. S.",
                        text = text,
                        postedAt = "Just now"
                    )
                    _state.update { it.copy(
                        comments = it.comments + comment,
                        commentInput = ""
                    )}
                }
            }

            NewsDetailIntent.ClearKeyboardFlag ->
                _state.update { it.copy(openKeyboard = false) }
        }
    }
}
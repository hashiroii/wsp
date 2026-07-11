package kz.kbtu.wsp.feature.news

import androidx.lifecycle.ViewModel
import kz.kbtu.wsp.core.domain.model.NewsComment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewsDetailViewModel : ViewModel() {

    private val _state = MutableStateFlow(NewsDetailState())
    val state: StateFlow<NewsDetailState> = _state.asStateFlow()

    private var commentCounter = 0

    fun load(newsId: String, openKeyboard: Boolean) {
        _state.update {
            it.copy(
                news = MOCK_ALL_NEWS.find { n -> n.id == newsId },
                comments = MOCK_COMMENTS[newsId] ?: emptyList(),
                openKeyboard = openKeyboard
            )
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
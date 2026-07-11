package kz.kbtu.wsp.feature.news

sealed class NewsDetailIntent {
    data class OnCommentInputChange(val text: String) : NewsDetailIntent()
    data object SendComment : NewsDetailIntent()
    data object ClearKeyboardFlag : NewsDetailIntent()
}
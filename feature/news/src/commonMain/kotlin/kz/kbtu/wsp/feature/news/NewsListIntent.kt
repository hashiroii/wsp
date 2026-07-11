package kz.kbtu.wsp.feature.news

sealed class NewsListIntent {
    data class OnSearchQueryChange(val query: String) : NewsListIntent()
    data class OnNewsClick(val newsId: String) : NewsListIntent()
    data class OnNewsCommentClick(val newsId: String) : NewsListIntent()
}
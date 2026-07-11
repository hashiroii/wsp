package kz.kbtu.wsp.feature.news

import kz.kbtu.wsp.core.domain.model.NewsComment
import kz.kbtu.wsp.core.domain.model.NewsItem

data class NewsDetailState(
    val news: NewsItem? = null,
    val comments: List<NewsComment> = emptyList(),
    val commentInput: String = "",
    val openKeyboard: Boolean = false
)
package kz.kbtu.wsp.core.domain.model

data class NewsItem(
    val id: String,
    val title: String,
    val body: String,
    val publishedAt: String,
    val commentCount: Int,
    val isPinned: Boolean = false
)

data class NewsComment(
    val id: String,
    val authorName: String,
    val text: String,
    val postedAt: String
)
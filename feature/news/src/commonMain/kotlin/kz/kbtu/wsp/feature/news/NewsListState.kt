package kz.kbtu.wsp.feature.news

import kz.kbtu.wsp.core.domain.model.NewsItem

data class NewsListState(
    val searchQuery: String = "",
    val pinnedNews: List<NewsItem> = emptyList(),
    val pagedNews: List<NewsItem> = emptyList()
) {
    val filteredPagedNews: List<NewsItem>
        get() = if (searchQuery.isBlank()) pagedNews
                else pagedNews.filter { it.title.contains(searchQuery, ignoreCase = true) }
}
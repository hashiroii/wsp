package kz.kbtu.wsp.core.domain.repository

import kz.kbtu.wsp.core.domain.model.NewsComment
import kz.kbtu.wsp.core.domain.model.NewsItem

interface NewsRepository {
    suspend fun getAllNews(): List<NewsItem>
    suspend fun getComments(newsId: String): List<NewsComment>
}
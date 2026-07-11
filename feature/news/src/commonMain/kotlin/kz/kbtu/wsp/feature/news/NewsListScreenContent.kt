package kz.kbtu.wsp.feature.news

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.kbtu.wsp.core.domain.model.NewsItem
import kz.kbtu.wsp.core.ui.icons.WspIcons

@Composable
fun NewsListScreenContent(
    state: NewsListState,
    onIntent: (NewsListIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val showSearch by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    Column(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = showSearch,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = { onIntent(NewsListIntent.OnSearchQueryChange(it)) },
                    placeholder = { Text("Search news...") },
                    leadingIcon = {
                        Icon(WspIcons.Search, contentDescription = null, modifier = Modifier.size(20.dp))
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
                HorizontalDivider()
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (state.pinnedNews.isNotEmpty()) {
                item {
                    Text(
                        "Featured",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                items(state.pinnedNews, key = { it.id }) { news ->
                    NewsCard(
                        news = news,
                        onClick = { onIntent(NewsListIntent.OnNewsClick(news.id)) },
                        onCommentClick = { onIntent(NewsListIntent.OnNewsCommentClick(news.id)) }
                    )
                }
                item {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "All news",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            items(state.filteredPagedNews, key = { it.id }) { news ->
                NewsCard(
                    news = news,
                    onClick = { onIntent(NewsListIntent.OnNewsClick(news.id)) },
                    onCommentClick = { onIntent(NewsListIntent.OnNewsCommentClick(news.id)) }
                )
            }
        }
    }
}

@Composable
private fun NewsCard(
    news: NewsItem,
    onClick: () -> Unit,
    onCommentClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 0.dp)
        ) {
            Text(
                text = news.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = news.body,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(12.dp))
            CommentRow(
                date = news.publishedAt,
                commentCount = news.commentCount,
                onCommentClick = onCommentClick
            )
        }
    }
}

@Composable
private fun CommentRow(
    date: String,
    commentCount: Int,
    onCommentClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = date,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(6.dp))
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onCommentClick)
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val label = when (commentCount) {
                0 -> "Leave a comment"
                1 -> "1 comment"
                else -> "$commentCount comments"
            }
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = WspIcons.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
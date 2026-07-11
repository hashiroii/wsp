package kz.kbtu.wsp.feature.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewsListScreen(
    onNewsClick: (String) -> Unit,
    onCommentClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    NewsListScreenContent(
        state = state,
        onIntent = { intent ->
            when (intent) {
                is NewsListIntent.OnNewsClick        -> onNewsClick(intent.newsId)
                is NewsListIntent.OnNewsCommentClick -> onCommentClick(intent.newsId)
                else                                 -> viewModel.onIntent(intent)
            }
        },
        modifier = modifier
    )
}
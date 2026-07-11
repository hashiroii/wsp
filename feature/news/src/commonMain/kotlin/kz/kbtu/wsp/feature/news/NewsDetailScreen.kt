package kz.kbtu.wsp.feature.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewsDetailScreen(
    newsId: String,
    openKeyboard: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewsDetailViewModel = koinViewModel()
) {
    LaunchedEffect(newsId) { viewModel.load(newsId, openKeyboard) }
    val state by viewModel.state.collectAsState()
    NewsDetailScreenContent(
        state = state,
        onBack = onBack,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}
package kz.kbtu.wsp.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = remember { HomeViewModel() }
) {
    val state by viewModel.state.collectAsState()
    HomeScreenContent(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}
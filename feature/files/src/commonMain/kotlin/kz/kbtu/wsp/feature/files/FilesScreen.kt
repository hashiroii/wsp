package kz.kbtu.wsp.feature.files

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FilesScreen(
    modifier: Modifier = Modifier,
    viewModel: FilesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    FilesScreenContent(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}
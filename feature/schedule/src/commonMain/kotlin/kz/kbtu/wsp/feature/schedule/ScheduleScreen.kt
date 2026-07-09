package kz.kbtu.wsp.feature.schedule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    ScheduleScreenContent(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}
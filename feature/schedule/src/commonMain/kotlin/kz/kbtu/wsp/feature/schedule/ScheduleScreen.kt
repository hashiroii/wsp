package kz.kbtu.wsp.feature.schedule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = remember { ScheduleViewModel() }
) {
    val state by viewModel.state.collectAsState()
    ScheduleScreenContent(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}
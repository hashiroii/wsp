package kz.kbtu.wsp.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onNavigate: (HomeIntent) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = remember { HomeViewModel() }
) {
    val state by viewModel.state.collectAsState()
    HomeScreenContent(
        state = state,
        onIntent = { intent ->
            when (intent) {
                HomeIntent.OnAttestationClick,
                HomeIntent.OnTranscriptClick,
                HomeIntent.OnFxRegistrationClick,
                HomeIntent.OnCourseRegistrationClick,
                HomeIntent.OnAddDropClick,
                HomeIntent.OnFinancialClick,
                HomeIntent.OnNewsClick,
                HomeIntent.OnAttendanceClick,
                HomeIntent.OnMapClick,
                HomeIntent.OnLostFoundClick -> onNavigate(intent)
                else -> viewModel.onIntent(intent)
            }
        },
        modifier = modifier
    )
}
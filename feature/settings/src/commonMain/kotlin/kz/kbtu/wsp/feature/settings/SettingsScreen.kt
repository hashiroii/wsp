package kz.kbtu.wsp.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    onOpenNotificationSettings: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    SettingsScreenContent(
        state = state,
        onIntent = { intent ->
            if (intent == SettingsIntent.OpenNotificationSettings) {
                onOpenNotificationSettings()
            } else {
                viewModel.onIntent(intent)
            }
        },
        modifier = modifier
    )
}
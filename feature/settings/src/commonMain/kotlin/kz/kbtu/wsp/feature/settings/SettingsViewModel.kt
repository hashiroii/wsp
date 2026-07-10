package kz.kbtu.wsp.feature.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kz.kbtu.wsp.core.ui.ThemeManager

class SettingsViewModel(private val themeManager: ThemeManager) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState(theme = themeManager.preference))
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    fun onIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.SetTheme -> {
                themeManager.preference = intent.preference
                _state.update { it.copy(theme = intent.preference) }
            }
            SettingsIntent.OpenNotificationSettings -> Unit
        }
    }
}
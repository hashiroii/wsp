package kz.kbtu.wsp.feature.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            // Navigation intents are handled by the caller via callbacks.
            // Data intents (load, refresh) will update _state here as features are built.
            else -> Unit
        }
    }
}
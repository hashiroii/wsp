package kz.kbtu.wsp.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.kbtu.wsp.core.domain.repository.ProfileRepository

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val profile = profileRepository.getProfile()
            _state.update { it.copy(profile = profile) }
        }
    }

    fun onIntent(intent: ProfileIntent) {
        when (intent) {
            ProfileIntent.ShowPhotoPreview -> _state.update { it.copy(showPhotoPreview = true) }
            ProfileIntent.DismissPhotoPreview -> _state.update { it.copy(showPhotoPreview = false) }
            is ProfileIntent.NavigateToSection -> _state.update { it.copy(activeSection = intent.section) }
        }
    }
}
package kz.kbtu.wsp.feature.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        ProfileState(
            profile = ProfileData(
                firstName = "Магжан",
                firstNameEn = "Magzhan",
                lastName = "Мубарак",
                lastNameEn = "Mubarak",
                middleName = null,
                iin = "040101XXXXXX",
                studentId = "2023XXXX",
                login = "m.mubarak",
                birthDate = "01.01.2004",
                sex = "Мужской",
                maritalStatus = "не состоит в браке",
                nationality = "Казах",
                citizenship = "Казахстан",
                isResident = true,
                email = "jasanasxat2@gmail.com",
                emailKbtu = "m.mubarak@kbtu.kz",
                mobilePhone = "7XXXXXXXXXX",
                category = "Студент",
                needsDorm = false,
                entranceYear = "2023-2024",
                studyForm = "лет: 4, сем. оконч. 2"
            )
        )
    )
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun onIntent(intent: ProfileIntent) {
        when (intent) {
            ProfileIntent.ShowPhotoPreview -> _state.update { it.copy(showPhotoPreview = true) }
            ProfileIntent.DismissPhotoPreview -> _state.update { it.copy(showPhotoPreview = false) }
            is ProfileIntent.NavigateToSection -> Unit
        }
    }
}
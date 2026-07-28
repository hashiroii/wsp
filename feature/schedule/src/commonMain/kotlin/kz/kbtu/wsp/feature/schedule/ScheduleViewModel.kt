package kz.kbtu.wsp.feature.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.kbtu.wsp.core.domain.repository.ScheduleRepository

class ScheduleViewModel(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        ScheduleState(
            selectedYear = currentAcademicYear(),
            selectedTerm = currentAcademicTerm(),
            selectedDay = currentScheduleDay()
        )
    )
    val state: StateFlow<ScheduleState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val studentEntries = scheduleRepository.getClassEntries()
            val examEntries = scheduleRepository.getExamEntries()
            _state.update {
                it.copy(
                    studentEntries = studentEntries,
                    examEntries = examEntries,
                    selectedExamDate = examEntries.firstOrNull()?.date
                )
            }
        }
    }

    fun onIntent(intent: ScheduleIntent) {
        when (intent) {
            is ScheduleIntent.SelectTab      -> _state.update { it.copy(activeTab = intent.tab) }
            is ScheduleIntent.SelectYear     -> _state.update { it.copy(selectedYear = intent.year) }
            is ScheduleIntent.SelectTerm     -> _state.update { it.copy(selectedTerm = intent.term) }
            is ScheduleIntent.SelectDay      -> _state.update { it.copy(selectedDay = intent.day) }
            is ScheduleIntent.SelectExamDate -> _state.update { it.copy(selectedExamDate = intent.date) }
        }
    }
}
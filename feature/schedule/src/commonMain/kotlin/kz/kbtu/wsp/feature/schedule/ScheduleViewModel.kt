package kz.kbtu.wsp.feature.schedule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScheduleViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        ScheduleState(
            selectedYear = currentAcademicYear(),
            selectedTerm = currentAcademicTerm(),
            selectedDay = currentScheduleDay(),
            studentEntries = mockClassEntries(),
            examEntries = mockExamEntries(),
            selectedExamDate = mockExamEntries().firstOrNull()?.date
        )
    )
    val state: StateFlow<ScheduleState> = _state.asStateFlow()

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

// ── Mock data ─────────────────────────────────────────────────────────────────

private fun mockClassEntries(): List<ClassEntry> = listOf(
    ClassEntry("c1",  ScheduleDay.Mon, "08:00", "Calculus 2",         ClassType.Lecture,  "B3-101",  "Abylkhozhin Z."),
    ClassEntry("c2",  ScheduleDay.Mon, "10:00", "Linear Algebra",     ClassType.Seminar,  "B2-214",  "Moldagaliyeva A."),
    ClassEntry("c3",  ScheduleDay.Mon, "14:00", "Physics",            ClassType.Lab,      "A1-Lab2", "Ospanov T."),
    ClassEntry("c4",  ScheduleDay.Tue, "08:00", "Discrete Math",      ClassType.Lecture,  "B3-101",  "Kobzhanova A."),
    ClassEntry("c5",  ScheduleDay.Tue, "11:00", "Algorithms",         ClassType.Seminar,  "IT-205",  "Kobzhanova A."),
    ClassEntry("c6",  ScheduleDay.Tue, "16:00", "English",            ClassType.Practice, "D1-204",  "Chalova A."),
    ClassEntry("c7",  ScheduleDay.Wed, "09:00", "Calculus 2",         ClassType.Seminar,  "B2-310",  "Abylkhozhin Z."),
    ClassEntry("c8",  ScheduleDay.Wed, "13:00", "Physics",            ClassType.Lecture,  "A1-101",  "Ospanov T."),
    ClassEntry("c9",  ScheduleDay.Wed, "18:00", "Algorithms",         ClassType.Lab,      "IT-Lab1", "Kobzhanova A."),
    ClassEntry("c10", ScheduleDay.Thu, "08:00", "Linear Algebra",     ClassType.Lecture,  "B3-101",  "Moldagaliyeva A."),
    ClassEntry("c11", ScheduleDay.Thu, "11:00", "Discrete Math",      ClassType.Seminar,  "IT-202",  "Kobzhanova A."),
    ClassEntry("c12", ScheduleDay.Thu, "14:00", "Physics",            ClassType.Lab,      "A1-Lab3", "Ospanov T."),
    ClassEntry("c13", ScheduleDay.Fri, "09:00", "Algorithms",         ClassType.Lecture,  "IT-205",  "Kobzhanova A."),
    ClassEntry("c14", ScheduleDay.Fri, "13:00", "Calculus 2",         ClassType.Seminar,  "B2-214",  "Abylkhozhin Z."),
    ClassEntry("c15", ScheduleDay.Fri, "16:00", "Linear Algebra",     ClassType.Lab,      "B2-Lab1", "Moldagaliyeva A."),
    ClassEntry("c16", ScheduleDay.Sat, "09:00", "English",            ClassType.Seminar,  "D1-101",  "Chalova A."),
    ClassEntry("c17", ScheduleDay.Sat, "11:00", "Elective: Robotics", ClassType.Lab,      "IT-Lab2", "Ospanov T.")
)

private fun mockExamEntries(): List<ExamEntry> = listOf(
    ExamEntry("e1", "14 May", "08:30", "11:30", "Calculus 2",    "B3-101"),
    ExamEntry("e2", "14 May", "13:00", "16:00", "Discrete Math", "IT-205"),
    ExamEntry("e3", "16 May", "08:30", "11:30", "Linear Algebra","B3-101"),
    ExamEntry("e4", "16 May", "14:00", "17:00", "English",       "D1-101"),
    ExamEntry("e5", "19 May", "10:00", "13:00", "Algorithms",    "IT-205"),
    ExamEntry("e6", "21 May", "08:30", "11:30", "Physics",       "A1-101")
)
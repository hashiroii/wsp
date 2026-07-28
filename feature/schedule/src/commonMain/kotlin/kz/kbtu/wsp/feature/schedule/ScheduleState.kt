package kz.kbtu.wsp.feature.schedule

import kz.kbtu.wsp.core.domain.model.ClassEntry
import kz.kbtu.wsp.core.domain.model.ExamEntry
import kz.kbtu.wsp.core.domain.model.ScheduleDay

data class ScheduleState(
    val activeTab: ScheduleTab = ScheduleTab.Student,
    val selectedYear: AcademicYear = AcademicYear(2025),
    val selectedTerm: AcademicTerm = AcademicTerm.Summer2,
    val selectedDay: ScheduleDay = ScheduleDay.Sat,
    val studentEntries: List<ClassEntry> = emptyList(),
    val examEntries: List<ExamEntry> = emptyList(),
    val selectedExamDate: String? = null
)
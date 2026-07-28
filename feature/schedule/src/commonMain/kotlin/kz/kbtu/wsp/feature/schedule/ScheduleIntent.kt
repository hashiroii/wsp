package kz.kbtu.wsp.feature.schedule

import kz.kbtu.wsp.core.domain.model.ScheduleDay

sealed class ScheduleIntent {
    data class SelectTab(val tab: ScheduleTab) : ScheduleIntent()
    data class SelectYear(val year: AcademicYear) : ScheduleIntent()
    data class SelectTerm(val term: AcademicTerm) : ScheduleIntent()
    data class SelectDay(val day: ScheduleDay) : ScheduleIntent()
    data class SelectExamDate(val date: String) : ScheduleIntent()
}

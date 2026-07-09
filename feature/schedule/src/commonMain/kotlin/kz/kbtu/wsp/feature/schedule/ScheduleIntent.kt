package kz.kbtu.wsp.feature.schedule

sealed class ScheduleIntent {
    data class SelectTab(val tab: ScheduleTab) : ScheduleIntent()
}
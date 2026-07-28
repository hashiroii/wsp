package kz.kbtu.wsp.feature.schedule

import androidx.compose.ui.graphics.Color
import kz.kbtu.wsp.core.domain.model.ClassType
import kz.kbtu.wsp.core.domain.model.ScheduleDay

data class AcademicYear(val startYear: Int) {
    val label: String get() = "$startYear–${startYear + 1}"
}

enum class AcademicTerm {
    Fall, Spring, Summer1, Summer2, Summer3, Winter;

    val displayName: String
        get() = when (this) {
            Fall    -> "Fall"
            Spring  -> "Spring"
            Summer1 -> "Summer 1"
            Summer2 -> "Summer 2"
            Summer3 -> "Summer 3"
            Winter  -> "Winter"
        }
}

val ClassType.accentColor: Color
    get() = when (this) {
        ClassType.Lecture  -> Color(0xFF1565C0)
        ClassType.Seminar  -> Color(0xFF2E7D32)
        ClassType.Lab      -> Color(0xFFE65100)
        ClassType.Practice -> Color(0xFF6A1B9A)
    }

val ClassType.containerColor: Color
    get() = when (this) {
        ClassType.Lecture  -> Color(0xFFE3F2FD)
        ClassType.Seminar  -> Color(0xFFE8F5E9)
        ClassType.Lab      -> Color(0xFFFFF3E0)
        ClassType.Practice -> Color(0xFFF3E5F5)
    }

val SCHEDULE_HOURS: List<String> = (8..22).map { h -> "${if (h < 10) "0$h" else "$h"}:00" }

fun currentAcademicYear(): AcademicYear {
    val year = currentYear()
    return if (currentMonth() >= 9) AcademicYear(year) else AcademicYear(year - 1)
}

fun currentAcademicTerm(): AcademicTerm = when (currentMonth()) {
    9, 10, 11 -> AcademicTerm.Fall
    12        -> AcademicTerm.Winter
    1, 2, 3, 4, 5 -> AcademicTerm.Spring
    6         -> AcademicTerm.Summer1
    7         -> AcademicTerm.Summer2
    8         -> AcademicTerm.Summer3
    else      -> AcademicTerm.Fall
}

fun currentScheduleDay(): ScheduleDay = ScheduleDay.entries[currentDayOfWeek()]

fun availableAcademicYears(): List<AcademicYear> {
    val current = currentAcademicYear().startYear
    return (current - 2..current + 1).map { AcademicYear(it) }
}
package kz.kbtu.wsp.core.domain.model

enum class ScheduleDay { Mon, Tue, Wed, Thu, Fri, Sat, Sun }

enum class ClassType { Lecture, Seminar, Lab, Practice }

data class ClassEntry(
    val id: String,
    val day: ScheduleDay,
    val startTime: String,
    val subject: String,
    val type: ClassType,
    val room: String,
    val professor: String
)

data class ExamEntry(
    val id: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val subject: String,
    val room: String
)
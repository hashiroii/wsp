package kz.kbtu.wsp.core.domain.repository

import kz.kbtu.wsp.core.domain.model.ClassEntry
import kz.kbtu.wsp.core.domain.model.ExamEntry

interface ScheduleRepository {
    suspend fun getClassEntries(): List<ClassEntry>
    suspend fun getExamEntries(): List<ExamEntry>
}
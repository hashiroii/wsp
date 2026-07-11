package kz.kbtu.wsp.feature.schedule

import java.time.LocalDate

actual fun currentYear(): Int = LocalDate.now().year
actual fun currentMonth(): Int = LocalDate.now().monthValue
actual fun currentDayOfWeek(): Int = LocalDate.now().dayOfWeek.ordinal  // Mon=0..Sun=6
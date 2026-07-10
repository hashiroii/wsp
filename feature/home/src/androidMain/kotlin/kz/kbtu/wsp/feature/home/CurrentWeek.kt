package kz.kbtu.wsp.feature.home

import java.time.LocalDate
import java.time.temporal.IsoFields

actual fun currentWeekOfYear(): Int =
    LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)
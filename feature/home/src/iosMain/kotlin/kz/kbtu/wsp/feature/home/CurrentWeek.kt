package kz.kbtu.wsp.feature.home

import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitWeekOfYear
import platform.Foundation.NSDate

actual fun currentWeekOfYear(): Int =
    NSCalendar.currentCalendar.component(NSCalendarUnitWeekOfYear, fromDate = NSDate()).toInt()
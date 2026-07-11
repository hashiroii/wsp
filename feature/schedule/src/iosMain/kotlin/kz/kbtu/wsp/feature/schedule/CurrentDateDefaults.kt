package kz.kbtu.wsp.feature.schedule

import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitMonth
import platform.Foundation.NSCalendarUnitWeekday
import platform.Foundation.NSCalendarUnitYear
import platform.Foundation.NSDate

actual fun currentYear(): Int =
    NSCalendar.currentCalendar.component(NSCalendarUnitYear, fromDate = NSDate()).toInt()

actual fun currentMonth(): Int =
    NSCalendar.currentCalendar.component(NSCalendarUnitMonth, fromDate = NSDate()).toInt()

actual fun currentDayOfWeek(): Int {
    // NSCalendar: 1=Sun, 2=Mon, …, 7=Sat → convert to 0=Mon..6=Sun
    val nsWeekday = NSCalendar.currentCalendar
        .component(NSCalendarUnitWeekday, fromDate = NSDate()).toInt()
    return (nsWeekday - 2 + 7) % 7
}
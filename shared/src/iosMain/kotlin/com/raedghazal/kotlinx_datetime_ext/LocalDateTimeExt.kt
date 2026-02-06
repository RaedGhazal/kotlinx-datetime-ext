package com.raedghazal.kotlinx_datetime_ext

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number
import platform.Foundation.NSCalendar
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents

@OptIn(ExperimentalForeignApi::class)
fun LocalDateTime.toNsDate(): NSDate? {
    val calendar = NSCalendar.currentCalendar
    val components = NSDateComponents()
    components.year = this.year.convert()
    components.month = this.month.number.convert()
    components.day = this.day.convert()
    components.hour = this.hour.convert()
    components.minute = this.minute.convert()
    components.second = this.second.convert()

    return calendar.dateFromComponents(components)
}

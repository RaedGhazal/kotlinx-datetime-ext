package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * JavaScript Date class.
 */
external class Date() : JsAny {
    constructor(time: JsNumber)

    fun getTime(): JsNumber
}


/**
 * Converts [LocalDateTime] to JavaScript [Date].
 */
fun LocalDateTime.toDate(): Date {
    return Date(this.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds().toDouble().toJsNumber())
}

/**
 * Converts [LocalDate] to JavaScript [Date].
 */
fun LocalDate.toDate(): Date {
    return Date(this.atStartOfDayIn(TimeZone.currentSystemDefault()).toEpochMilliseconds().toDouble().toJsNumber())
}

/**
 * Converts [LocalTime] to JavaScript [Date].
 */
fun LocalTime.toDate(): Date {
    return this.atDate(LocalDate.now()).toDate()
}

/**
 * Converts JavaScript [Date] to [LocalDateTime].
 */
fun Date.toLocalDateTime(): LocalDateTime {
    return Instant.fromEpochMilliseconds(getTime().toDouble().toLong()).toLocalDateTime(TimeZone.currentSystemDefault())
}

/**
 * Converts JavaScript [Date] to [LocalDate].
 */
fun Date.toLocalDate(): LocalDate {
    return toLocalDateTime().date
}

/**
 * Converts JavaScript [Date] to [LocalTime].
 */
fun Date.toLocalTime(): LocalTime {
    return toLocalDateTime().time
}

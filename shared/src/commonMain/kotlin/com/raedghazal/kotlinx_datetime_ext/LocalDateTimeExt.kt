package com.raedghazal.kotlinx_datetime_ext

import kotlin.time.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

const val DAY_HOURS = 24L
val LocalTime.Companion.MIN get() = LocalTime(0, 0)
val LocalTime.Companion.MAX get() = LocalTime(23, 59, 59, 999999999)

fun LocalDateTime.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
    return Clock.System.now().toLocalDateTime(timeZone)
}

fun LocalDate.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate {
    return LocalDateTime.now(timeZone).date
}

fun LocalTime.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalTime {
    return LocalDateTime.now(timeZone).time
}

fun LocalDate.atStartOfDay(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
    return this.atStartOfDayIn(timeZone).toLocalDateTime(timeZone)
}

fun LocalDate.atEndOfDay(): LocalDateTime {
    return LocalDateTime(this, LocalTime.MAX)
}

fun LocalDate.atEndOfDay(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
    return this
        .plus(1, DateTimeUnit.DAY)
        .atStartOfDayIn(timeZone).minus(1.nanoseconds)
        .toLocalDateTime(timeZone)
}

infix fun LocalDateTime.durationUntil(end: LocalDateTime): Duration {
    val timeZone = TimeZone.currentSystemDefault()

    return end.toInstant(timeZone) - this.toInstant(timeZone)
}
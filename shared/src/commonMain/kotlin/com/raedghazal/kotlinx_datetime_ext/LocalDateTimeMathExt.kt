package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration

operator fun LocalDateTime.plus(duration: Duration): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    return this.toInstant(timeZone)
        .plus(duration)
        .toLocalDateTime(timeZone)
}

fun LocalDateTime.plus(value: Long, unit: DateTimeUnit.TimeBased): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    return this.toInstant(timeZone)
        .plus(value, unit)
        .toLocalDateTime(timeZone)
}

fun LocalDateTime.plus(dayValue: Long, dayUnit: DateTimeUnit.DayBased): LocalDateTime {
    val value = dayValue * dayUnit.days * DAY_HOURS
    val unit = DateTimeUnit.HOUR

    return plus(value, unit)
}

operator fun LocalDateTime.minus(duration: Duration): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    return this.toInstant(timeZone)
        .minus(duration)
        .toLocalDateTime(timeZone)
}

fun LocalDateTime.minus(value: Long, unit: DateTimeUnit.TimeBased): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    return this.toInstant(timeZone)
        .minus(value, unit)
        .toLocalDateTime(timeZone)
}

fun LocalDateTime.minus(dayValue: Long, dayUnit: DateTimeUnit.DayBased): LocalDateTime {
    val value = dayValue * dayUnit.days * DAY_HOURS
    val unit = DateTimeUnit.HOUR

    return minus(value, unit)
}
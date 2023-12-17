package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

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
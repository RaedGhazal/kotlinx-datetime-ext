package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaLocalTime
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toKotlinLocalDateTime
import kotlinx.datetime.toKotlinLocalTime
import java.time.format.DateTimeFormatter

actual class LocalDateTimeFormatter private constructor(
    private val pattern: String,
    private val locale: Locale
) {

    private val formatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofPattern(pattern, locale.javaLocale)
    }

    actual fun format(localDateTime: LocalDateTime): String {
        return formatter.format(localDateTime.toJavaLocalDateTime())
    }

    actual fun format(localTime: LocalTime): String {
        return formatter.format(localTime.toJavaLocalTime())
    }

    actual fun format(localDate: LocalDate): String {
        return formatter.format(localDate.toJavaLocalDate())
    }

    actual fun parseToLocalDateTime(str: String): LocalDateTime {
        return java.time.LocalDateTime
            .parse(str, formatter)
            .toKotlinLocalDateTime()
    }

    actual fun parseToLocalDate(str: String): LocalDate {
        return java.time.LocalDate
            .parse(str, formatter)
            .toKotlinLocalDate()
    }

    actual fun parseToLocalTime(str: String): LocalTime {
        return java.time.LocalTime
            .parse(str, formatter)
            .toKotlinLocalTime()
    }

    actual companion object {
        actual fun ofPattern(pattern: String, locale: Locale): LocalDateTimeFormatter {
            return LocalDateTimeFormatter(pattern, locale)
        }
    }
}
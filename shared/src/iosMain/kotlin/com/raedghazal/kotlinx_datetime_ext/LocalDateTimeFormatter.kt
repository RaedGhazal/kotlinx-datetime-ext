package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.atTime
import kotlinx.datetime.toKotlinInstant
import kotlinx.datetime.toLocalDateTime
import platform.Foundation.NSDateFormatter

actual class LocalDateTimeFormatter private constructor(
    private val pattern: String,
    private val locale: Locale
) {

    private val formatter: NSDateFormatter by lazy {
        NSDateFormatter().apply {
            dateFormat = pattern
            locale = this@LocalDateTimeFormatter.locale.nsLocale
        }
    }

    /**
     * @throws IllegalStateException localDateTime cannot be converted to NSDate
     */
    actual fun format(localDateTime: LocalDateTime): String {
        val date = localDateTime.toNsDate()
            ?: throw IllegalStateException("Failed to convert LocalDateTime $LocalDateTime to NSDate")
        return NSDateFormatter().apply {
            dateFormat = pattern
            locale = this@LocalDateTimeFormatter.locale.nsLocale
        }.stringFromDate(date)
    }

    actual fun format(localTime: LocalTime): String {
        val dateTime = localTime.atDate(LocalDate.now())
        return format(dateTime)
    }

    actual fun format(localDate: LocalDate): String {
        val dateTime = localDate.atTime(LocalTime.MIN)
        return format(dateTime)
    }

    /**
     * @throws IllegalStateException if the string cannot be parsed
     */
    actual fun parseToLocalDateTime(str: String): LocalDateTime {
        return formatter
            .dateFromString(str)
            ?.toKotlinInstant()
            ?.toLocalDateTime(TimeZone.currentSystemDefault())
            ?: throw IllegalStateException("Failed to convert String $str to LocalDateTime")
    }

    /**
     * @throws IllegalStateException if the string cannot be parsed
     */
    actual fun parseToLocalDate(str: String): LocalDate {
        return formatter
            .dateFromString(str)
            ?.toKotlinInstant()
            ?.toLocalDateTime(TimeZone.currentSystemDefault())
            ?.date
            ?: throw IllegalStateException("Failed to convert String $str to LocalDate")
    }

    /**
     * @throws IllegalStateException if the string cannot be parsed
     */
    actual fun parseToLocalTime(str: String): LocalTime {
        return formatter
            .dateFromString(str)
            ?.toKotlinInstant()
            ?.toLocalDateTime(TimeZone.currentSystemDefault())
            ?.time
            ?: throw IllegalStateException("Failed to convert String $str to LocalTime")
    }

    actual companion object {
        actual fun ofPattern(pattern: String, locale: Locale): LocalDateTimeFormatter {
            return LocalDateTimeFormatter(pattern, locale)
        }
    }
}
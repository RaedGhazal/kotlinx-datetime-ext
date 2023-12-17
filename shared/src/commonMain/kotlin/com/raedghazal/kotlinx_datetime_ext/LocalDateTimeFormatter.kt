package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

expect class LocalDateTimeFormatter {

    fun format(localDateTime: LocalDateTime): String
    fun format(localTime: LocalTime): String
    fun format(localDate: LocalDate): String
    fun parseToLocalDateTime(str: String): LocalDateTime
    fun parseToLocalDate(str: String): LocalDate
    fun parseToLocalTime(str: String): LocalTime

    companion object {
        fun ofPattern(pattern: String, locale: Locale): LocalDateTimeFormatter
    }
}
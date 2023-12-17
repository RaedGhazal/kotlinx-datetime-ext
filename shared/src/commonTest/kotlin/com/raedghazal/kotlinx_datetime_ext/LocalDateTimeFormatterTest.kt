package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeFormatterTest {

    @Test
    fun `test format LocalDateTime FullDateTimeFormatter`() {
        val formatter = FullDateTimeFormatter
        val localDateTime = LocalDateTime(2023, 1, 1, 1, 1)
        val expected = "01/01/2023 - 01:01"

        assertEquals(expected, formatter.format(localDateTime))
    }

    @Test
    fun `test format LocalDateTime MonthYearFormatter`() {
        val formatter = MonthYearFormatter
        val localDateTime = LocalDateTime(2023, 1, 1, 1, 1)
        val expected = "01/2023"

        assertEquals(expected, formatter.format(localDateTime))
    }

    @Test
    fun `test format LocalDateTime DateFormatter`() {
        val formatter = DateFormatter
        val localDateTime = LocalDate(2023, 1, 1)
        val expected = "01/01/2023"

        assertEquals(expected, formatter.format(localDateTime))
    }

    @Test
    fun `test format LocalDateTime TimeFormatter`() {
        val formatter = TimeFormatter
        val localDateTime = LocalTime(1, 1, 1)
        val expected = "01:01"

        assertEquals(
            expected,
            formatter.format(localDateTime)
        )
    }

    @Test
    fun `test parse to SqlDateTimeFormatter`() {
        val formatter = SqlDateTimeFormatter
        val localDateTime = LocalDateTime(2023, 1, 1, 1, 1)
        assertEquals(
            localDateTime,
            formatter.parseToLocalDateTime("2023-01-01 01:01:00")
        )
    }

    @Test
    fun `test parse DateFormatter to LocalDate`() {
        val formatter = DateFormatter
        val expected = LocalDate(2023, 1, 1)
        assertEquals(
            expected,
            formatter.parseToLocalDate("01/01/2023")
        )
    }

    @Test
    fun `test parse TimeFormatter to LocalTime`() {
        val formatter = TimeFormatter
        val expected = LocalTime(1, 8, 0)
        assertEquals(
            expected,
            formatter.parseToLocalTime("01:08")
        )
    }

    companion object {
        val SqlDateTimeFormatter =
            LocalDateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.en())
        val FullDateTimeFormatter: LocalDateTimeFormatter = LocalDateTimeFormatter
            .ofPattern("dd/MM/yyyy - HH:mm", Locale.en())
        val MonthYearFormatter: LocalDateTimeFormatter = LocalDateTimeFormatter
            .ofPattern("MM/yyyy", Locale.en())
        val DateFormatter: LocalDateTimeFormatter = LocalDateTimeFormatter
            .ofPattern("dd/MM/yyyy", Locale.en())
        val TimeFormatter: LocalDateTimeFormatter = LocalDateTimeFormatter
            .ofPattern("HH:mm", Locale.en())
    }
}
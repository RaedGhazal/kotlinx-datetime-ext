package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class LocalDateTimeMathExtTest {

    @Test
    fun `add hours and minutes to LocalDateTime by Duration`() {
        val localDateTime = LocalDateTime(2023, 1, 1, 0, 0)
        val expected = LocalDateTime(2023, 1, 1, 7, 35)
        assertEquals(expected, localDateTime + 35.minutes + 7.hours)
    }

    @Test
    fun `minus days to LocalDateTime by Duration`() {
        val localDateTime = LocalDateTime(2023, 5, 14, 0, 0)
        val expected = LocalDateTime(2023, 5, 7, 0, 0)
        assertEquals(expected, localDateTime - 7.days)
    }

    @Test
    fun `add minutes to start of year`() {
        val localDateTime = LocalDateTime(2023, 1, 1, 0, 0)
        val expected = LocalDateTime(2023, 1, 1, 0, 2)

        assertEquals(expected, localDateTime.plus(2, DateTimeUnit.MINUTE))
        assertEquals(expected, localDateTime + 2.minutes)
    }

    @Test
    fun `add hours to end of the day`() {
        val localDateTime = LocalDateTime(2023, 1, 7, 21, 0)
        val expected = LocalDateTime(2023, 1, 8, 4, 0)

        assertEquals(expected, localDateTime.plus(7, DateTimeUnit.HOUR))
        assertEquals(expected, localDateTime + 7.hours)
    }

    @Test
    fun `add days to beginning of the month`() {
        val localDateTime = LocalDateTime(2023, 1, 1, 0, 0)
        val expected = LocalDateTime(2023, 1, 6, 0, 0)

        assertEquals(expected, localDateTime.plus(5, DateTimeUnit.DAY))
        assertEquals(expected, localDateTime + 5.days)
    }

    @Test
    fun `add days to end of the month`() {
        val localDateTime = LocalDateTime(2023, 1, 31, 0, 0)
        val expected = LocalDateTime(2023, 2, 5, 0, 0)

        assertEquals(expected, localDateTime.plus(5, DateTimeUnit.DAY))
        assertEquals(expected, localDateTime + 5.days)
    }

    @Test
    fun `subtract minutes from start of the year`() {
        val localDateTime = LocalDateTime(2023, 1, 1, 0, 0)
        val expected = LocalDateTime(2022, 12, 31, 23, 58)

        assertEquals(expected, localDateTime.minus(2, DateTimeUnit.MINUTE))
        assertEquals(expected, localDateTime - 2.minutes)
    }

    @Test
    fun `subtract hours from end of the day`() {
        val localDateTime = LocalDateTime(2023, 1, 7, 21, 0)
        val expected = LocalDateTime(2023, 1, 7, 14, 0)

        assertEquals(expected, localDateTime.minus(7, DateTimeUnit.HOUR))
        assertEquals(expected, localDateTime - 7.hours)
    }

    @Test
    fun `subtract days from beginning of the month`() {
        val localDateTime = LocalDateTime(2023, 7, 1, 0, 0)
        val expected = LocalDateTime(2023, 6, 30, 0, 0)

        assertEquals(expected, localDateTime.minus(1, DateTimeUnit.DAY))
        assertEquals(expected, localDateTime - 1.days)
    }

    @Test
    fun `subtract days from end of the month`() {
        val localDateTime = LocalDateTime(2023, 1, 31, 0, 0)
        val expected = LocalDateTime(2023, 1, 28, 0, 0)

        assertEquals(expected, localDateTime.minus(3, DateTimeUnit.DAY))
        assertEquals(expected, localDateTime - 3.days)
    }

}
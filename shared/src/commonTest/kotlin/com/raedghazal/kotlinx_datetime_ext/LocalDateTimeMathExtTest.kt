package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeMathExtTest {

    @Test
    fun `add 2 hours to start of the day`() {
        val localDateTime = LocalDateTime(2021, 1, 1, 0, 0)
        val expected = LocalDateTime(2021, 1, 1, 0, 2)

        assertEquals(expected, localDateTime.plus(2, DateTimeUnit.MINUTE))
    }

    @Test
    fun `add 7 hours to end of the day`() {
        val localDateTime = LocalDateTime(2021, 1, 7, 21, 0)
        val expected = LocalDateTime(2021, 1, 8, 4, 0)

        assertEquals(expected, localDateTime.plus(7, DateTimeUnit.HOUR))
    }

    @Test
    fun `add 5 days to beginning of the month`() {
        val localDateTime = LocalDateTime(2021, 1, 1, 0, 0)
        val expected = LocalDateTime(2021, 1, 6, 0, 0)

        assertEquals(expected, localDateTime.plus(5, DateTimeUnit.DAY))
    }

    @Test
    fun `add 5 days to end of the month`() {
        val localDateTime = LocalDateTime(2021, 1, 31, 0, 0)
        val expected = LocalDateTime(2021, 2, 5, 0, 0)

        assertEquals(expected, localDateTime.plus(5, DateTimeUnit.DAY))
    }


    @Test
    fun `subtract 2 minutes from start of the year`() {
        val localDateTime = LocalDateTime(2021, 1, 1, 0, 0)
        val expected = LocalDateTime(2020, 12, 31, 23, 58)

        assertEquals(expected, localDateTime.minus(2, DateTimeUnit.MINUTE))
    }

    @Test
    fun `subtract 7 hours from end of the day`() {
        val localDateTime = LocalDateTime(2021, 1, 7, 21, 0)
        val expected = LocalDateTime(2021, 1, 7, 14, 0)

        assertEquals(expected, localDateTime.minus(7, DateTimeUnit.HOUR))
    }

    @Test
    fun `subtract 1 days from beginning of the month`() {
        val localDateTime = LocalDateTime(2021, 7, 1, 0, 0)
        val expected = LocalDateTime(2021, 6, 30, 0, 0)

        assertEquals(expected, localDateTime.minus(1, DateTimeUnit.DAY))
    }

    @Test
    fun `subtract 3 days from end of the month`() {
        val localDateTime = LocalDateTime(2021, 1, 31, 0, 0)
        val expected = LocalDateTime(2021, 1, 28, 0, 0)

        assertEquals(expected, localDateTime.minus(3, DateTimeUnit.DAY))
    }


}
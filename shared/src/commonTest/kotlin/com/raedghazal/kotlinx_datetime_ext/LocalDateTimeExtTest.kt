package com.raedghazal.kotlinx_datetime_ext

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeExtTest {

    @Test
    fun `test startOfDay`() {
        val localDate = LocalDate(2023, 12, 17).atStartOfDay()
        val expected = LocalDateTime(2023, 12, 17, 0, 0)

        assertEquals(expected, localDate)
    }

    @Test
    fun `test endOfDay`() {
        val localDate = LocalDate(2023, 12, 17).atEndOfDay()
        val expected = LocalDateTime(2023, 12, 17, 23, 59, 59, 999999999)

        assertEquals(expected, localDate)
    }

}
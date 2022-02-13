package com.example.diary.data

import org.junit.Assert.assertEquals
import org.junit.Test
import java.sql.Timestamp

class TimestampTypeConverterTest {

    private val testTimestampConverter: TimestampTypeConverter = TimestampTypeConverter()

    @Test
    fun testConverter() {
        val checkDate: Timestamp = Timestamp.valueOf("2022-07-12 12:24:15")
        val expected = 1657617855000
        assertEquals(expected, testTimestampConverter.longFromTimestamp(checkDate))
    }
}
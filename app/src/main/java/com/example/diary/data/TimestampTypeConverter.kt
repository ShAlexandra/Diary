package com.example.diary.data

import androidx.room.TypeConverter
import java.sql.Timestamp

class TimestampTypeConverter {

    @TypeConverter
    fun longFromTimestamp(date:Timestamp?): Long? {
        return date?.time
    }

    @TypeConverter
    fun timestampFromLong(date: Long?): Timestamp?{
        return date?.let { Timestamp(it) }
    }
}
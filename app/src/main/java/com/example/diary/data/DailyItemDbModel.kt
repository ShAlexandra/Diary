package com.example.diary.data

import java.sql.Timestamp
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName="daily_items")
@TypeConverters(TimestampTypeConverter::class)
data class DailyItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date_start: Timestamp,
    val date_finish: Timestamp,
    val name: String,
    val description: String,

)
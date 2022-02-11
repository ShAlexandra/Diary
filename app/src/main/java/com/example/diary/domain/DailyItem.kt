package com.example.diary.domain

import java.sql.Timestamp

data class DailyItem(
    val date_start: Timestamp,
    val date_finish: Timestamp,
    val name: String,
    val description: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}

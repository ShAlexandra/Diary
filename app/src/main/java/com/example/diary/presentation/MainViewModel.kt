package com.example.diary.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.diary.domain.DailyItem
import com.example.diary.domain.GetDailyListUseCase
import java.sql.Timestamp
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getDailyListUseCase: GetDailyListUseCase
) : ViewModel() {

    private var start: Long = UNKNOWN_START

    fun getStartDateForList(year: Int, month: Int, day: Int): LiveData<List<DailyItem>> {
        start = Timestamp.valueOf("$year-$month-$day 00:00:00").time
        return getDailyListUseCase.getDailyList(start)
    }

    companion object {

        const val UNKNOWN_START: Long = 0
    }

}
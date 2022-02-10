package com.example.diary.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.diary.data.DiaryRepositoryImpl
import com.example.diary.domain.DailyItem
import com.example.diary.domain.GetDailyListUseCase
import java.sql.Timestamp

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DiaryRepositoryImpl(application)

    private val getDailyListUseCase = GetDailyListUseCase(repository)

    private var start: Long = UNKNOWN_START

    fun getStartDateForList(year: Int, month: Int, day: Int):LiveData<List<DailyItem>> {
        start = Timestamp.valueOf("$year-$month-$day 00:00:00").time
        return getDailyListUseCase.getDailyList(start)
    }

    companion object {

        const val UNKNOWN_START: Long = 0
    }

}
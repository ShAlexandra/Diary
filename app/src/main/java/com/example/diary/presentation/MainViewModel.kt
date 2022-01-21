package com.example.diary.presentation

import androidx.lifecycle.ViewModel
import com.example.diary.data.DiaryRepositoryImpl
import com.example.diary.domain.DailyItem
import com.example.diary.domain.DeleteDailyItemUseCase
import com.example.diary.domain.GetDailyListUseCase

class MainViewModel:ViewModel() {

    private val repository=DiaryRepositoryImpl

    private val getDailyListUseCase=GetDailyListUseCase(repository)
    private val deleteDailyItemUseCase= DeleteDailyItemUseCase(repository)

    val diaryList=getDailyListUseCase.getDailyList()

    fun deleteDailyItem(dailyItem: DailyItem){
        deleteDailyItemUseCase.deleteDailyItem(dailyItem)
    }

    //fun changeEnableState  разберемся с изменением в другом окне позже
}
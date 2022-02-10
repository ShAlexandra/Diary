package com.example.diary.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diary.data.DiaryRepositoryImpl
import com.example.diary.domain.DailyItem
import com.example.diary.domain.GetDailyItemUseCase

class DailyItemOverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DiaryRepositoryImpl(application)

    private val getDailyItemUseCase = GetDailyItemUseCase(repository)

    private val _dailyItem = MutableLiveData<DailyItem>()
    val dailyItem: LiveData<DailyItem>
        get() = _dailyItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getDailyItem(shopItemId: Int) {
        val item = getDailyItemUseCase.getDailyItem(shopItemId)
        _dailyItem.value = item
    }

    fun finishOverview(){
        finishWork()
    }

    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
}
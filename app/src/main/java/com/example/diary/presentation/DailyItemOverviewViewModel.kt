package com.example.diary.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diary.domain.DailyItem
import com.example.diary.domain.GetDailyItemUseCase
import javax.inject.Inject

class DailyItemOverviewViewModel @Inject constructor(
    private val getDailyItemUseCase: GetDailyItemUseCase
) : ViewModel() {

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

    fun finishOverview() {
        finishWork()
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}
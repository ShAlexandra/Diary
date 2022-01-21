package com.example.diary.domain

import androidx.lifecycle.LiveData

interface DiaryRepository {

    fun addDailyItem(dailyItem: DailyItem)

    fun deleteDailyItem(dailyItem: DailyItem)

    fun editDailyItem(dailyItem: DailyItem)

    fun getDailyItem(shopDailyId: Int): DailyItem

    fun getDailyList(): LiveData<List<DailyItem>>
}
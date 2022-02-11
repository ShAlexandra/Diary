package com.example.diary.domain

import androidx.lifecycle.LiveData

interface DiaryRepository {

    fun addDailyItem(dailyItem: DailyItem)

    fun getDailyItem(shopDailyId: Int): DailyItem

    fun getDailyList(start: Long): LiveData<List<DailyItem>>
}
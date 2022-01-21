package com.example.diary.domain

interface DiaryRepository {

    fun addDailyItem(dailyItem: DailyItem)

    fun deleteDailyItem(dailyItem: DailyItem)

    fun editDailyItem(dailyItem: DailyItem)

    fun getDailyItem(shopDailyId: Int): DailyItem

    fun getDailyList(): List<DailyItem>
}
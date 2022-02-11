package com.example.diary.domain

import javax.inject.Inject

class AddDailyItemUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {

    fun addDailyItem(dailyItem: DailyItem) {
        diaryRepository.addDailyItem(dailyItem)
    }
}
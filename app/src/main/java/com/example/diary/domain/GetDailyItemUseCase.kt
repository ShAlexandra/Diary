package com.example.diary.domain

import javax.inject.Inject

class GetDailyItemUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    fun getDailyItem(dailyItemId: Int): DailyItem {
        return diaryRepository.getDailyItem(dailyItemId)
    }
}
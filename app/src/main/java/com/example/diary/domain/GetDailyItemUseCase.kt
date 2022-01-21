package com.example.diary.domain

class GetDailyItemUseCase(private val diaryRepository: DiaryRepository){

    fun getDailyItem(dailyItemId: Int):DailyItem{
        return diaryRepository.getDailyItem(dailyItemId)
    }
}
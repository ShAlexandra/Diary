package com.example.diary.domain

class AddDailyItemUseCase(private val diaryRepository: DiaryRepository) {

    fun addDailyItemUseCase(dailyItem: DailyItem){
        diaryRepository.addDailyItem(dailyItem)
    }
}
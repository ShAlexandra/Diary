package com.example.diary.domain

class AddDailyItemUseCase(private val diaryRepository: DiaryRepository) {

    fun addDailyItem(dailyItem: DailyItem){
        diaryRepository.addDailyItem(dailyItem)
    }
}
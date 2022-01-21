package com.example.diary.domain

class DeleteDailyItemUseCase(private val diaryRepository: DiaryRepository) {

    fun deleteDailyItem(dailyItem: DailyItem){
        diaryRepository.deleteDailyItem(dailyItem)
    }
}
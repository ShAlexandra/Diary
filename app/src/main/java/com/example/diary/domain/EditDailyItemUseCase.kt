package com.example.diary.domain

class EditDailyItemUseCase(private val diaryRepository: DiaryRepository) {

    fun editDailyItem(dailyItem: DailyItem){
        diaryRepository.editDailyItem(dailyItem)
    }
}
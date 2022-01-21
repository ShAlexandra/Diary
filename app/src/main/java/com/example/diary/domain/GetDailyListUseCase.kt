package com.example.diary.domain

class GetDailyListUseCase(private val diaryRepository: DiaryRepository) {

    fun getDailyList():List<DailyItem>{
        return diaryRepository.getDailyList()
    }
}
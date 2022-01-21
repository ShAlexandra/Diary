package com.example.diary.domain

import androidx.lifecycle.LiveData

class GetDailyListUseCase(private val diaryRepository: DiaryRepository) {

    fun getDailyList():LiveData<List<DailyItem>>{
        return diaryRepository.getDailyList()
    }
}
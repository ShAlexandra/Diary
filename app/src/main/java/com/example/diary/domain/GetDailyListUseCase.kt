package com.example.diary.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetDailyListUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {

    fun getDailyList(start: Long): LiveData<List<DailyItem>> {
        return diaryRepository.getDailyList(start)
    }
}
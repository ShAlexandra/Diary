package com.example.diary.domain

import android.util.Log
import androidx.lifecycle.LiveData

class GetDailyListUseCase(private val diaryRepository: DiaryRepository) {

    fun getDailyList(start:Long):LiveData<List<DailyItem>>{
        Log.d("in_UseCase", start.toString())
        return diaryRepository.getDailyList(start)
    }
}
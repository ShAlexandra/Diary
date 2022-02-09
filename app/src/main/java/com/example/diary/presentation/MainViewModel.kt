package com.example.diary.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.diary.data.DiaryRepositoryImpl
import com.example.diary.domain.DailyItem
import com.example.diary.domain.GetDailyListUseCase

class MainViewModel(application: Application):AndroidViewModel(application) {

    private val repository=DiaryRepositoryImpl(application)

    private val getDailyListUseCase=GetDailyListUseCase(repository)

    val diaryList=getDailyListUseCase.getDailyList()

}
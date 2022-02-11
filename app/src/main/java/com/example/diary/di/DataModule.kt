package com.example.diary.di

import android.app.Application
import com.example.diary.data.AppDatabase
import com.example.diary.data.DailyListDao
import com.example.diary.data.DiaryRepositoryImpl
import com.example.diary.domain.DiaryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindDailyListRepository(impl: DiaryRepositoryImpl): DiaryRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideDailyListDao(application: Application): DailyListDao {
            return AppDatabase.getInstance(application).dailyListDao()
        }
    }
}
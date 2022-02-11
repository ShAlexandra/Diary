package com.example.diary.di

import androidx.lifecycle.ViewModel
import com.example.diary.presentation.DailyItemOverviewViewModel
import com.example.diary.presentation.DailyItemViewModel
import com.example.diary.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DailyItemViewModel::class)
    fun bindDailyItemViewModel(viewModel: DailyItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DailyItemOverviewViewModel::class)
    fun bindDailyItemOverviewViewModel(viewModel: DailyItemOverviewViewModel): ViewModel
}
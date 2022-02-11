package com.example.diary.di

import android.app.Application
import com.example.diary.presentation.DailyItemActivity
import com.example.diary.presentation.DailyItemOverviewActivity
import com.example.diary.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: DailyItemActivity)

    fun inject(activity: DailyItemOverviewActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
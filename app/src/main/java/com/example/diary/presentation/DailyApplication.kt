package com.example.diary.presentation

import android.app.Application
import com.example.diary.di.DaggerApplicationComponent

class DailyApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
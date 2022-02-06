package com.example.diary.presentation

import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.diary.R

class DailyItemActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_item)
        val calendar=findViewById<CalendarView>(R.id.calendarView)
    }
}
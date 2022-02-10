package com.example.diary.presentation

import android.os.Bundle
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var dailyListAdapter: DailyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupRecyclerView()
        viewModel.getStartDateForList(LocalDateTime.now().year, LocalDateTime.now().monthValue, LocalDateTime.now().dayOfMonth).observe(this) {
            dailyListAdapter.submitList(it)
        }
        buttonListener()
        calendarListener()
    }

    private fun calendarListener(){
        val changeDate = findViewById<CalendarView>(R.id.cv_date_list)
        changeDate.setOnDateChangeListener{p0, year, month, day ->
            viewModel.getStartDateForList(year,month+1, day).observe(this) {
                dailyListAdapter.submitList(it)
            }
        }
    }

    private fun buttonListener() {
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_new_item)
        buttonAddItem.setOnClickListener {
            val intent = DailyItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val rvDailyList = findViewById<RecyclerView>(R.id.rv_daily_list)
        with(rvDailyList) {
            dailyListAdapter = DailyListAdapter()
            adapter = dailyListAdapter
        }
        setupClickListener()
    }

    private fun setupClickListener(){
        dailyListAdapter.onDailyItemClickListener={
            val intent=DailyItemOverviewActivity.newIntentOverviewItem(this, it.id)
            startActivity(intent)
        }
    }
}
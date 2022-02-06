package com.example.diary.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var dailyListAdapter: DailyListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.diaryList.observe(this) {
            dailyListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val rvDailyList = findViewById<RecyclerView>(R.id.rv_daily_list)
        with(rvDailyList){
            dailyListAdapter = DailyListAdapter()
            adapter = dailyListAdapter
        }
    }
}
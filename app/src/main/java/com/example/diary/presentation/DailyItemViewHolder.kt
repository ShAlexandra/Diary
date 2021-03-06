package com.example.diary.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.R

class DailyItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvTime: TextView = view.findViewById(R.id.tv_time)
    val tvName: TextView = view.findViewById(R.id.tv_name)
}
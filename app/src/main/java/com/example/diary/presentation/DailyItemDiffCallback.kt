package com.example.diary.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.diary.domain.DailyItem

class DailyItemDiffCallback: DiffUtil.ItemCallback<DailyItem>() {

    override fun areItemsTheSame(oldItem: DailyItem, newItem: DailyItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DailyItem, newItem: DailyItem): Boolean {
        return oldItem == newItem
    }
}
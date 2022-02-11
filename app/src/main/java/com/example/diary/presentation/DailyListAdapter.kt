package com.example.diary.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.diary.R
import com.example.diary.domain.DailyItem

class DailyListAdapter : ListAdapter<DailyItem, DailyItemViewHolder>(DailyItemDiffCallback()) {

    var onButtonAddingClickListener: ((DailyItem) -> Unit)? = null
    var onDailyItemClickListener: ((DailyItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false)
        return DailyItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: DailyItemViewHolder, position: Int) {
        val dailyItem = getItem(position)
        viewHolder.view.setOnClickListener {
            onButtonAddingClickListener?.invoke(dailyItem)
        }
        viewHolder.view.setOnClickListener {
            onDailyItemClickListener?.invoke(dailyItem)
        }
        viewHolder.tvName.text = dailyItem.name
        viewHolder.tvTime.text =
            "${
                dailyItem.date_start.toString().substringAfter(' ').substringBefore(':')
            }.00 - " +
                    "${
                        dailyItem.date_finish.toString().substringAfter(' ').substringBefore(':')
                    }.00"
    }
}
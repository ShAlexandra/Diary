package com.example.diary.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diary.domain.DailyItem
import com.example.diary.domain.DiaryRepository
import java.sql.Timestamp
import java.text.SimpleDateFormat

object DiaryRepositoryImpl : DiaryRepository {

    private val dailyListLD = MutableLiveData<List<DailyItem>>()
    private val dailyList = mutableListOf<DailyItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 13) {
            //позже, возможно, буду задавать реальную дату, чтобы сменять дни. Пока будет просто час ненастоящего дня
            val startTime = Timestamp((3600000 * 8 + i * 3600000).toLong())
            val finishTime = Timestamp((3600000 * 8 + (i + 1) * 3600000).toLong())
            val item = DailyItem(startTime, finishTime, "name$i", "description")
            addDailyItem(item)
        }
    }

    override fun addDailyItem(dailyItem: DailyItem) {
        if (dailyItem.id == DailyItem.UNDEFINED_ID) {
            dailyItem.id = autoIncrementId++
        }
        dailyList.add(dailyItem)
        updateList()
    }

    override fun deleteDailyItem(dailyItem: DailyItem) {
        dailyList.remove(dailyItem)
        updateList()
    }

    override fun editDailyItem(dailyItem: DailyItem) {
        val oldElement = getDailyItem(dailyItem.id)
        dailyList.remove(oldElement)
        addDailyItem(dailyItem)
    }

    override fun getDailyItem(dailyItemId: Int): DailyItem {
        return dailyList.find {
            it.id == dailyItemId
        } ?: throw RuntimeException("Element with id $dailyItemId not found")
    }

    override fun getDailyList(): LiveData<List<DailyItem>> {
        return dailyListLD
    }

    private fun updateList() {
        dailyListLD.value = dailyList.toList()
    }
}
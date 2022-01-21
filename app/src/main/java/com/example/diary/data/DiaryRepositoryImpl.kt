package com.example.diary.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diary.domain.DailyItem
import com.example.diary.domain.DiaryRepository
import java.lang.RuntimeException

object DiaryRepositoryImpl : DiaryRepository {

    private val dailyListLD = MutableLiveData<List<DailyItem>>()
    private val dailyList = mutableListOf<DailyItem>()

    private var autoIncrementId = 0

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

    private fun updateList(){
        dailyListLD.value= dailyList.toList()
    }
}
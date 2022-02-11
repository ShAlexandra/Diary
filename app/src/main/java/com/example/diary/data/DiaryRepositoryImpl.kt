package com.example.diary.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.diary.domain.DailyItem
import com.example.diary.domain.DiaryRepository
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val dailyListDao: DailyListDao,
    private val mapper: DailyListMapper
) : DiaryRepository {

    override fun addDailyItem(dailyItem: DailyItem) {
        dailyListDao.addShopItem(mapper.mapEntityToDbModel(dailyItem))
    }


    override fun getDailyItem(dailyItemId: Int): DailyItem {
        val dbModel = dailyListDao.getDailyItem(dailyItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getDailyList(start: Long): LiveData<List<DailyItem>> = Transformations.map(
        dailyListDao.getDailyList(start)
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

}
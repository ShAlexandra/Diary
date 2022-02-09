package com.example.diary.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.diary.domain.DailyItem
import com.example.diary.domain.DiaryRepository
import java.sql.Timestamp
import java.text.SimpleDateFormat

class DiaryRepositoryImpl(
    application: Application
) : DiaryRepository {

    private val dailyListDao=AppDatabase.getInstance(application).dailyListDao()
    private val mapper=DailyListMapper()



    override fun addDailyItem(dailyItem: DailyItem) {
        dailyListDao.addShopItem(mapper.mapEntityToDbModel(dailyItem))
    }


    override fun getDailyItem(dailyItemId: Int): DailyItem {
        val dbModel=dailyListDao.getDailyItem(dailyItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getDailyList(): LiveData<List<DailyItem>> = Transformations.map(
        dailyListDao.getDailyList()
    ){
        mapper.mapListDbModelToListEntity(it)
    }

}
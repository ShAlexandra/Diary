package com.example.diary.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DailyListDao {

    //TODO переделать на список по дате
    @Query("SELECT * FROM daily_items")
    fun getDailyList(): LiveData<List<DailyItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(dailyItemDbModel: DailyItemDbModel)

    @Query("SELECT * FROM daily_items WHERE id=:dailyItemId LIMIT 1")
    fun getDailyItem(dailyItemId: Int): DailyItemDbModel
}
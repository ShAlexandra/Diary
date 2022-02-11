package com.example.diary.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DailyListDao {

    //@Query("SELECT * FROM daily_items WHERE (date_start >= :start AND date_start<:start+86400000) ORDER BY date_start")
    @Query("SELECT * FROM daily_items WHERE date_start BETWEEN :start AND (:start+86400000) ORDER BY date_start")
    fun getDailyList(start: Long): LiveData<List<DailyItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(dailyItemDbModel: DailyItemDbModel)

    @Query("SELECT * FROM daily_items WHERE id=:dailyItemId LIMIT 1")
    fun getDailyItem(dailyItemId: Int): DailyItemDbModel
}
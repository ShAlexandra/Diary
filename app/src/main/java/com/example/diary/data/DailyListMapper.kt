package com.example.diary.data

import com.example.diary.domain.DailyItem
import javax.inject.Inject

class DailyListMapper @Inject constructor() {

    fun mapEntityToDbModel(dailyItem: DailyItem) = DailyItemDbModel(
        id = dailyItem.id,
        date_start = dailyItem.date_start,
        date_finish = dailyItem.date_finish,
        name = dailyItem.name,
        description = dailyItem.description
    )

    fun mapDbModelToEntity(dailyItemDbModel: DailyItemDbModel) = DailyItem(
        id = dailyItemDbModel.id,
        date_start = dailyItemDbModel.date_start,
        date_finish = dailyItemDbModel.date_finish,
        name = dailyItemDbModel.name,
        description = dailyItemDbModel.description
    )

    fun mapListDbModelToListEntity(list: List<DailyItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
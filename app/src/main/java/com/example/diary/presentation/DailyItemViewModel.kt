package com.example.diary.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diary.data.DiaryRepositoryImpl
import com.example.diary.domain.AddDailyItemUseCase
import com.example.diary.domain.DailyItem
import com.example.diary.domain.GetDailyItemUseCase
import java.sql.Timestamp

class DailyItemViewModel : ViewModel() {

    private val repository = DiaryRepositoryImpl

    private val getDailyItemUseCase = GetDailyItemUseCase(repository)
    private val addDailyItemUseCase = AddDailyItemUseCase(repository)

    private val _dailyItem = MutableLiveData<DailyItem>()
    val dailyItem: LiveData<DailyItem>
        get() = _dailyItem

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getDailyItem(dailyItemId: Int) {
        val item = getDailyItemUseCase.getDailyItem(dailyItemId)
        _dailyItem.value = item
    }

    fun addDailyItem(
        inputDate: Long,
        inputTime: String,
        inputName: String?,
        inputDescription: String?
    ) { //TODO ПРОВЕРИТЬ!!!
        val name = parseStringInput(inputName)
        val description = parseStringInput(inputDescription)
        val dateStart =
            inputDate + (inputTime[0].digitToInt() * 10 + inputTime[1].digitToInt()) * 3600000
        val dateFinish = dateStart + 3600000
        inputDate + inputTime[0].digitToInt() * 10 + inputTime[1].digitToInt()
        if (validateInput(name)) {//невалидным мб только имя
            val dailyItem = DailyItem(Timestamp(dateStart), Timestamp(dateFinish), name, description)
            addDailyItemUseCase.addDailyItem(dailyItem)
            finishWork()
        }
    }

    private fun parseStringInput(inputValue: String?): String {
        return inputValue?.trim() ?: ""
    }

    private fun validateInput(name: String): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}

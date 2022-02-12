package com.example.diary.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diary.domain.AddDailyItemUseCase
import com.example.diary.domain.DailyItem
import java.sql.Timestamp
import javax.inject.Inject

class DailyItemViewModel @Inject constructor(
    private val addDailyItemUseCase: AddDailyItemUseCase
) : ViewModel() {

    private val _dailyItem = MutableLiveData<DailyItem>()
    val dailyItem: LiveData<DailyItem>
        get() = _dailyItem

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun addDailyItem(
        inputYear: Int,
        inputMonth: Int,
        inputDay: Int,
        inputTime: String,
        inputName: String?,
        inputDescription: String?
    ) {
        val name = parseStringInput(inputName)
        val description = parseStringInput(inputDescription)
        val timeStart = inputTime.substringBefore('.').toInt()
        val dateStart = Timestamp.valueOf("$inputYear-$inputMonth-$inputDay ${timeStart}:00:00")
        val dateFinish =
            Timestamp.valueOf("$inputYear-$inputMonth-$inputDay ${timeStart + 1}:00:00")
        if (validateInput(name)) {//невалидным мб только имя
            val dailyItem = DailyItem(dateStart, dateFinish, name, description)
            addDailyItemUseCase.addDailyItem(dailyItem)
            finishWork()
        }
    }


    private fun parseStringInput(inputValue: String?): String {
        return inputValue?.trim() ?: ""
    }

     fun validateInput(name: String): Boolean {
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

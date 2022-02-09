package com.example.diary.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diary.data.DiaryRepositoryImpl
import com.example.diary.domain.AddDailyItemUseCase
import com.example.diary.domain.DailyItem
import com.example.diary.domain.GetDailyItemUseCase
import java.sql.Timestamp
import kotlin.time.Duration.Companion.days

class DailyItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DiaryRepositoryImpl(application)

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
        inputYear: Int,
        inputMonth: Int,
        inputDay: Int,
        inputTime: String,
        inputName: String?,
        inputDescription: String?
    ) {
        Log.d("inputTime",inputTime)
        val name = parseStringInput(inputName)
        val description = parseStringInput(inputDescription)
        //val timeStart="${inputTime[0]}${inputTime[1]}".toInt()
        val timeStart=inputTime.substringBefore('.').toInt()
        val dateStart = Timestamp.valueOf("$inputYear-$inputMonth-$inputDay ${timeStart}:00:00")
        val dateFinish=Timestamp.valueOf("$inputYear-$inputMonth-$inputDay ${timeStart+1}:00:00")
        Log.d("OUR_DATE",dateStart.toString())
        if (validateInput(name)) {//невалидным мб только имя
            val dailyItem = DailyItem(dateStart, dateFinish, name, description)
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

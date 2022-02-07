package com.example.diary.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import com.example.diary.R
import com.example.diary.domain.DailyItem
import com.google.android.material.textfield.TextInputLayout
import java.sql.Timestamp
import java.time.DayOfWeek
import java.time.Month
import java.time.Year

class DailyItemActivity : AppCompatActivity() {

    private lateinit var viewModel: DailyItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var tilDescription: TextInputLayout
    private lateinit var etDescription: EditText
    private lateinit var calendarView: CalendarView
    private lateinit var buttonSave: Button
    private lateinit var spinTime: Spinner

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = DailyItem.UNDEFINED_ID

    private var calYear: Int = UNSPECIFIED
    private var calMonth: Int = UNSPECIFIED
    private var calDay: Int = UNSPECIFIED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_item)
        parseIntent()
        viewModel = ViewModelProvider(this)[DailyItemViewModel::class.java]
        initViews()
        addTextChangeListener()
        launchRightMode()
        observeViewModel()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode don't exist here")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_WATCH && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
    }

    private fun initViews() {
        tilName = findViewById(R.id.til_name)
        etName = findViewById(R.id.et_name)
        tilDescription = findViewById(R.id.til_description)
        etDescription = findViewById(R.id.et_description)
        calendarView = findViewById(R.id.calendarView)
        buttonSave = findViewById(R.id.save_button)
        spinTime = findViewById(R.id.spin_time)
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_WATCH -> launchWatchMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchWatchMode() {
        TODO()
    }

    private fun launchAddMode() {
        buttonSave.setOnClickListener {
            if (calYear == UNSPECIFIED && calMonth == UNSPECIFIED && calDay == UNSPECIFIED) {
                val newData = Timestamp(calendarView.date).toString()
                calYear = newData.substringBefore('-').toInt()
                calMonth = newData.substringAfter('-').substringBefore('-').toInt()
                calDay =
                    newData.substringAfter('-').substringAfter('-').
                    substringBefore(' ').toInt()
                viewModel.addDailyItem(
                    calYear,
                    calMonth,
                    calDay,
                    spinTime.selectedItem.toString(),
                    etName.text?.toString(),
                    etDescription.text?.toString()
                )
            } else {
                Log.d("input_fucking_time", spinTime.toString())
                viewModel.addDailyItem(
                    calYear,
                    calMonth,
                    calDay,
                    spinTime.selectedItem.toString(),
                    etName.text?.toString(),
                    etDescription.text?.toString()
                )
            }
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputName.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            tilName.error = message
        }
        viewModel.shouldCloseScreen.observe(this) {
            finish()
        }
    }

    private fun addTextChangeListener() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }
        })
        calendarView.setOnDateChangeListener { p0, year, month, day ->
            calYear = year
            calMonth = month
            calDay = day
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_ADD = "mode_add"
        private const val MODE_WATCH = "mode_watch"
        private const val MODE_UNKNOWN = ""
        private const val UNSPECIFIED = -1

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, DailyItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }
    }
}
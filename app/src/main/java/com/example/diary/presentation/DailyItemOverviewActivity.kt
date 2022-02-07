package com.example.diary.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.diary.R
import com.example.diary.domain.DailyItem

class DailyItemOverviewActivity : AppCompatActivity() {

    private lateinit var viewModel: DailyItemOverviewViewModel

    private lateinit var tvName: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvTime: TextView
    private lateinit var tvDescription: TextView
    private lateinit var buttonBack: Button

    private var screenMode = MODE_UNKNOWN
    private var dailyItemId = DailyItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_daily_item)
        parseIntent()
        viewModel = ViewModelProvider(this)[DailyItemOverviewViewModel::class.java]
        initViews()
        initFields()
        buttonBack.setOnClickListener {
            viewModel.finishOverview()
        }
        viewModel.shouldCloseScreen.observe(this){
            finish()
        }
    }

    private fun initFields() {
        viewModel.getDailyItem(dailyItemId)
        viewModel.dailyItem.observe(this) {
            tvName.text = it.name
            tvDate.text = it.date_start.toString().substringBefore(' ')
            tvTime.text = "${it.date_start.toString().substringAfter(' ').
            substringBefore(':')}.00 - ${it.date_finish.toString().
            substringAfter(' ').substringBefore(':')}.00"
            tvDescription.text = it.description
        }
    }

    private fun parseIntent() {
        if (!intent.hasExtra(DailyItemOverviewActivity.EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode don't exist here")
        }
        val mode = intent.getStringExtra(DailyItemOverviewActivity.EXTRA_SCREEN_MODE)
        if (mode != DailyItemOverviewActivity.MODE_WATCH &&
            mode != DailyItemOverviewActivity.MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_WATCH) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id don't exist here")
            }
            dailyItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, DailyItem.UNDEFINED_ID)
        }
    }

    private fun initViews() {
        tvName = findViewById(R.id.tv_name_over)
        tvDate = findViewById(R.id.tv_date_over)
        tvTime = findViewById(R.id.tv_time_over)
        tvDescription = findViewById(R.id.tv_description_over)
        buttonBack = findViewById(R.id.button_back)
    }

    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_WATCH = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentOverviewItem(context: Context, dailyItemId: Int): Intent {
            val intent = Intent(context, DailyItemOverviewActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_WATCH)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, dailyItemId)
            return intent
        }
    }
}
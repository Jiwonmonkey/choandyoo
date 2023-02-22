package com.example.myapplication_0125

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.activity_calendar.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName
    lateinit var mContext: Context

    var pageIndex = 0
    lateinit var currentDate: Date

    lateinit var calendar_year_month_text: TextView
    lateinit var calendar_layout: LinearLayout
    lateinit var calendar_view: RecyclerView
//    lateinit var calendarAdapter: CalendarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val dayText: TextView = findViewById(R.id.day_text)

        val dateFormat: DateFormat = SimpleDateFormat("yy년mm월dd일")

    }

    fun initView(view: View) {
        calendar_year_month_text = view.day_text
        calendar_layout = view.calendar_layout
        calendar_view = view.calendar_view

    }

}


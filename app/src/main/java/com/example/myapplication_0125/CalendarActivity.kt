package com.example.myapplication_0125

import android.media.Image
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_0125.Interface.OnItemListener
import com.example.myapplication_0125.adapter.CalendarAdapter
import com.example.myapplication_0125.databinding.ActivityCalendarBinding
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarActivity : AppCompatActivity(), OnItemListener {

    private lateinit var binding: ActivityCalendarBinding

    // 날짜 변수
    lateinit var selectedDate: LocalDateTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        //binding 초기화
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar)

        // 현재 날짜
        selectedDate = LocalDateTime.now()

        // 화면 설정
        setMonthView()

        // 이전 달 버튼 이벤트
        binding.preBtn.setOnClickListener {
            // 현재 월 -1 변수에 담기
            selectedDate = selectedDate.minusMonths(1)
            setMonthView()
        }

        // 다음달 버튼 이벤트
        binding.nextBtn.setOnClickListener {
            // 현재 월 +1 변수에 담기
            selectedDate = selectedDate.plusMonths(1)
            setMonthView()
        }
    }

    // 날짜 화면에 보여주기
    private fun setMonthView() {
        // 년월 텍스트뷰 세팅
        binding.dateText.text = yearMonthFromDate(selectedDate)

        // 날짜 생성해서 리스트에 담기
        val dayList = dayInMonthArray(selectedDate)

        // 어탭터 초기화
        val adapter = CalendarAdapter(dayList, this)

        // 레이아웃 설정(열 7개)
        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)

        // 레이아웃 적용
        binding.recyclerView.layoutManager = manager

        // 어댑터 적용
        binding.recyclerView.adapter = adapter
    }

    // 날짜 타입 설정
    private fun yearMonthFromDate(date: LocalDateTime): String {

        var formatter = DateTimeFormatter.ofPattern("yy년 MM월 dd일")

        // 받아온 날짜를 해당 포맷으로 변경
        return date.format(formatter)
    }


    // 날짜 생성
    private fun dayInMonthArray(date: LocalDateTime): ArrayList<String> {

        var dayList = ArrayList<String>()

        var yearMonth = YearMonth.from(date)

        // 해당 월 마지막 날짜 가져오기
        var lastDay = yearMonth.lengthOfMonth()

        // 해당 월 첫 번째 날 가져오기
        var firstDay = selectedDate.withDayOfMonth(1)

        // 첫 번째 날 요일 가져오기
        var dayOfWeek = firstDay.dayOfWeek.value

        for(i in 1..41) {
            if(i <= dayOfWeek || i > (lastDay + dayOfWeek)) {
                dayList.add("")
            }else {
                dayList.add((i-dayOfWeek).toString())
            }
        }
        return dayList
    }

    override fun onItemClick(dayText: String) {

        var clickDay = dayText.toInt()

        selectedDate = selectedDate.withDayOfMonth(clickDay)
        setMonthView()
    }
}



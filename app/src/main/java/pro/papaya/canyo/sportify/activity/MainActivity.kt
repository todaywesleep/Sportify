package pro.papaya.canyo.sportify.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.CalendarAdapter
import pro.papaya.canyo.sportify.model.Day
import pro.papaya.canyo.sportify.model.MonthStruct
import pro.papaya.canyo.sportify.utils.ArrayUtils
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {
  private lateinit var grid: GridView
  private lateinit var monthNameView: TextView

  private lateinit var nextButton: ImageView
  private lateinit var previousButton: ImageView

  private lateinit var dateAdapter: CalendarAdapter
  private val calendar = Calendar.getInstance()
  private val days: ArrayList<Day> = arrayListOf()

  private val onClickListener = View.OnClickListener { view ->
    when (view.id) {
      R.id.calendar_prev_button -> {
        toggleMonth(false)
      }

      R.id.calendar_next_button -> {
        toggleMonth(true)
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    calendar.firstDayOfWeek = Calendar.MONDAY

    setContentView(R.layout.activity_main)
    generateDayList()

    dateAdapter = CalendarAdapter(this, days)
    grid = findViewById(R.id.calendar_grid)
    monthNameView = findViewById(R.id.calendar_date_display)
    previousButton = findViewById(R.id.calendar_prev_button)
    nextButton = findViewById(R.id.calendar_next_button)
    grid.adapter = dateAdapter

    setHeadersState()
    bindListeners()
  }

  private fun bindListeners() {
    nextButton.setOnClickListener(onClickListener)
    previousButton.setOnClickListener(onClickListener)
  }

  @SuppressLint("SetTextI18n")
  private fun setHeadersState() {
    monthNameView.text =
            calendar.get(Calendar.YEAR).toString() + "  " + MonthStruct.values()[calendar.get(Calendar.MONTH)].name
  }

  private fun toggleMonth(next: Boolean) {
    val increase = if (next) 1 else -1

    calendar.add(Calendar.MONTH, increase)
    generateDayList()
    setHeadersState()
    dateAdapter.notifyDataSetChanged()
  }

  private fun generateDayList() {
    //Календарь для выставления инит даты
    val boofCalendar = Calendar.getInstance()

    //Структуры текущего месяца и кусочков прошедшего и грядущего
    val currentMonthDays = ArrayUtils.generateDaysArray(
            1,
            calendar.getActualMaximum(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.MONTH))
    val previousDays = arrayListOf<Day>()
    val finalDaysArray = arrayListOf<Day>()

    //Чистим день что бы правильно соотнести первый день месяца с днем недели
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    //Запись дней предыдущего месяца, который попадут на этот экран
    if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
      boofCalendar.add(Calendar.MONTH, -1)
      var maxDaysInPrevMonth = boofCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
      for (i in calendar.get(Calendar.DAY_OF_WEEK) downTo Calendar.MONDAY + 1 step 1) {
        previousDays.add(Day(maxDaysInPrevMonth, false, false))
        maxDaysInPrevMonth--
      }

      finalDaysArray.addAll(ArrayUtils.combineDayArrays(arrayListOf(previousDays, currentMonthDays)))
    }else{
      finalDaysArray.addAll(currentMonthDays)
    }

    //Докидываем дни со след месяца
    var day = 1
    while (finalDaysArray.size % 7 != 0) {
      finalDaysArray.add(Day(day, false, false))
      day++
    }

    this.days.clear()
    this.days.addAll(finalDaysArray)
  }
}
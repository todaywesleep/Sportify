package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.widget.GridView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.CalendarAdapter
import pro.papaya.canyo.sportify.model.Day
import pro.papaya.canyo.sportify.utils.ArrayUtils
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {
  private lateinit var grid: GridView
  private lateinit var dateAdapter: CalendarAdapter
  private val calendar = Calendar.getInstance()
  private val days: ArrayList<Day> = arrayListOf()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
    generateDayList()

    dateAdapter = CalendarAdapter(this, days)
    grid = findViewById(R.id.calendar_grid)
    grid.adapter = dateAdapter
  }

  private fun generateDayList() {
    //Календарь для выставления инит даты
    val boofCalendar = Calendar.getInstance()
    //Чистим день что бы правильно соотнести первый день месяца с днем недели
    calendar.set(Calendar.DAY_OF_MONTH, 0)

    //Структуры текущего месяца и кусочков прошедшего и грядущего
    val currentMonthDays = ArrayUtils.generateDaysArray(1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH), true)
    val previousDays = arrayListOf<Day>()
    val finalDaysArray = arrayListOf<Day>()

    //Запись дней предыдущего месяца, который попадут на этот экран
    if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
      boofCalendar.add(Calendar.MONTH, -1)
      var maxDaysInPrevMonth = boofCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
      for (i in calendar.get(Calendar.DAY_OF_WEEK) downTo Calendar.MONDAY step 1) {
        previousDays.add(Day(maxDaysInPrevMonth, false, false))
        maxDaysInPrevMonth--
      }

      finalDaysArray.addAll(ArrayUtils.combineDayArrays(arrayListOf(previousDays, currentMonthDays)))
    }
    calendar.time = Date()


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
package pro.papaya.canyo.sportify.activity.client

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.activity.BaseActivity
import pro.papaya.canyo.sportify.adapter.CalendarAdapter
import pro.papaya.canyo.sportify.fragment.DayInfoView
import pro.papaya.canyo.sportify.model.Day
import pro.papaya.canyo.sportify.model.MonthStruct
import pro.papaya.canyo.sportify.utils.ArrayUtils
import java.util.*

class MainClientActivity : BaseActivity(), CalendarAdapter.Callback {
  private lateinit var grid: GridView
  private lateinit var monthNameView: TextView

  private lateinit var nextButton: ImageView
  private lateinit var previousButton: ImageView
  private lateinit var dayInfo: DayInfoView

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

    dateAdapter.resetSelectedItem()
    dayInfo.getSelectedDayInfo(null)
  }

  override fun onItemPress(selectedDay: Day) {
    val dayObj = if (dateAdapter.getSelectedDay() == selectedDay) {
      null
    } else {
      selectedDay
    }

    dayInfo.getSelectedDayInfo(dayObj)
    grid.invalidateViews()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    calendar.firstDayOfWeek = Calendar.MONDAY

    setContentView(R.layout.activity_client_main)
    generateDayList()

    dateAdapter = CalendarAdapter(this, days, this)
    grid = findViewById(R.id.calendar_grid)
    monthNameView = findViewById(R.id.calendar_date_display)
    previousButton = findViewById(R.id.calendar_prev_button)
    nextButton = findViewById(R.id.calendar_next_button)
    dayInfo = findViewById(R.id.day_info)
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
    dateAdapter.setNewCalendar(calendar)
    dateAdapter.notifyDataSetChanged()
  }

  private fun generateDayList() {
    //Календарь для выставления инит даты и работы с пред. месяцем
    val boofCalendar = calendar.clone() as Calendar
    boofCalendar.add(Calendar.MONTH, -1)

    //Структуры текущего месяца и кусочков прошедшего и грядущего
    val currentMonthDays = ArrayUtils.generateDaysArray(calendar)
    val previousDays = arrayListOf<Day>()
    val finalDaysArray = arrayListOf<Day>()

    //Чистим день что бы правильно соотнести первый день месяца с днем недели
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    //Запись дней предыдущего месяца, который попадут на этот экран
    if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
      var maxDaysInPrevMonth = boofCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
      for (i in calendar.get(Calendar.DAY_OF_WEEK) downTo Calendar.MONDAY + 1 step 1) {
        previousDays.add(
                Day(maxDaysInPrevMonth,
                        boofCalendar.get(Calendar.MONTH),
                        boofCalendar.get(Calendar.YEAR)))
        maxDaysInPrevMonth--
      }
      previousDays.reverse()

      finalDaysArray.addAll(ArrayUtils.combineDayArrays(arrayListOf(previousDays, currentMonthDays)))
    } else {
      finalDaysArray.addAll(currentMonthDays)
    }

    //Добавить 2 месяца что былучить индекс некст месяца
    boofCalendar.add(Calendar.MONTH, 1)
    boofCalendar.add(Calendar.MONTH, 1)
    //Докидываем дни со след месяца
    var day = 1
    while (finalDaysArray.size % 7 != 0) {
      finalDaysArray.add(
              Day(
                      day,
                      boofCalendar.get(Calendar.MONTH),
                      boofCalendar.get(Calendar.YEAR)
              ))

      day++
    }

    this.days.clear()
    this.days.addAll(finalDaysArray)
  }
}
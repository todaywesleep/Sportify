package pro.papaya.canyo.sportify.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import pro.papaya.canyo.sportify.model.Day
import pro.papaya.canyo.sportify.view.CalendarDayItem
import java.util.*

class CalendarAdapter : BaseAdapter {
  interface Callback {
    fun onItemPress(selectedDay: Day)
  }

  private var selectedDate = Calendar.getInstance()

  private var selectedItem: Int? = null
  private var calendarDays = ArrayList<Day>()
  private val context: Context
  private val mCallback: Callback

  constructor(context: Context, calendarDays: ArrayList<Day>, callback: Callback) : super() {
    this.context = context
    this.calendarDays = calendarDays
    this.mCallback = callback
  }

  override fun getCount(): Int {
    return calendarDays.size
  }

  override fun getItem(position: Int): Any {
    return calendarDays[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val day = this.calendarDays[position]
    val dayView: View

    dayView = CalendarDayItem(context,
            day,
            selectedItem == position,
            (day.day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                    && day.month == Calendar.getInstance().get(Calendar.MONTH)
                    && day.year == Calendar.getInstance().get(Calendar.YEAR)),
            (day.month == selectedDate.get(Calendar.MONTH)
                    && day.year == selectedDate.get(Calendar.YEAR))
    ).calendarItem
    dayView.setOnClickListener(getOnItemClickListener(position))

    return dayView
  }

  private fun getOnItemClickListener(position: Int): View.OnClickListener {
    return View.OnClickListener {
      if (calendarDays[position].month == selectedDate.get(Calendar.MONTH)) {
        mCallback.onItemPress(calendarDays[position])

        selectedItem = if (selectedItem == position) null else position
      }
    }
  }

  fun setNewCalendar(newCalendar: Calendar) {
    this.selectedDate = newCalendar
  }

  fun getSelectedDay(): Day? {
    return if (selectedItem == null) null else calendarDays[selectedItem!!]
  }

  fun resetSelectedItem() {
    this.selectedItem = null
  }
}
package pro.papaya.canyo.sportify.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import pro.papaya.canyo.sportify.model.Day
import pro.papaya.canyo.sportify.view.CalendarDayItem

class CalendarAdapter : BaseAdapter {
  interface Callback {
    fun onItemPress(selectedDay: Day)
  }

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

    dayView = CalendarDayItem(context, day, selectedItem == position).calendarItem
    dayView.setOnClickListener(getOnItemClickListener(position))

    return dayView
  }

  private fun getOnItemClickListener(position: Int): View.OnClickListener {
    return View.OnClickListener {
      if (calendarDays[position].isCurrentMonth){
        mCallback.onItemPress(calendarDays[position])

        selectedItem = if (selectedItem == position) null else position
      }
    }
  }
}
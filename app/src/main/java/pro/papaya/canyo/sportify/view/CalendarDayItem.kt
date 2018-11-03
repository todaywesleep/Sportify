package pro.papaya.canyo.sportify.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.model.Day

class CalendarDayItem(context: Context, day: Day) : View(context) {
  lateinit var calendarItem: View
  private lateinit var textView: TextView

  init {
    if (context is Activity) {
      calendarItem = context.layoutInflater.inflate(R.layout.calendar_item_day, null)
      textView = calendarItem.findViewById(R.id.calendar_item_day_content)
      textView.text = day.day.toString()

      setUpDayItem(day)
    }
  }

  fun setUpDayItem(day: Day){
    if (day.isToday){
      textView.setBackgroundResource(R.drawable.calendar_item_today)
    }else if (day.isCurrentMonth){
      textView.setBackgroundResource(R.drawable.calendar_item_default)
    }
  }
}
package pro.papaya.canyo.sportify.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import pro.papaya.canyo.myapplication.R

class CalendarDayItem : View {
    private lateinit var calendarItem: View
    private lateinit var textView: TextView

    constructor(context: Context, day: Int): super(context) {
        if (context is Activity) {
            calendarItem = context.layoutInflater.inflate(R.layout.calendar_item_day, null)
            textView = calendarItem.findViewById(R.id.calendar_item_day_content)
            textView.text = day.toString()
        }
    }
}
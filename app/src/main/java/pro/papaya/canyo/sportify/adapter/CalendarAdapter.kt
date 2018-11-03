package pro.papaya.canyo.sportify.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.model.Day
import pro.papaya.canyo.sportify.view.CalendarDayItem

class CalendarAdapter: BaseAdapter {
    private var calendarDays = ArrayList<Day>()
    private val context: Context

    constructor(context: Context, calendarDays: ArrayList<Day>) : super() {
        this.context = context
        this.calendarDays = calendarDays
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

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): CalendarDayItem {
        val day = this.calendarDays[position]

        val dayView = CalendarDayItem(context, day.day)

        return dayView
    }
}
package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.CalendarAdapter
import pro.papaya.canyo.sportify.model.Day
import pro.papaya.canyo.sportify.utils.ArrayUtils
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity(){
    private lateinit var grid: GridView
    private lateinit var dateAdapter: CalendarAdapter
    private val calendar = Calendar.getInstance()
    private val days: ArrayList<Day> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        dateAdapter = CalendarAdapter(this, days)
        grid = findViewById(R.id.calendar_grid)
        grid.adapter = dateAdapter
        generateDayList()
    }

    private fun generateDayList(){
        val days = ArrayUtils.generateArray(0, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val previousDays = arrayListOf<Int>()
        val nextMonth = arrayListOf<Int>()
    }
}
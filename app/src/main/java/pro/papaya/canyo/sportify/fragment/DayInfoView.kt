package pro.papaya.canyo.sportify.fragment

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.model.Day
import pro.papaya.canyo.sportify.model.MonthStruct


class DayInfoView : RelativeLayout {
  constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
  constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
  constructor(context: Context) : super(context)

  private var nullDayView: TextView
  private var mainContainer: View
  private var dayInfoHeader: TextView
  private var aboutDayButton: View

  init {
    inflate(context, R.layout.day_info_view, this)

    nullDayView = findViewById(R.id.day_info_select_day)
    mainContainer = findViewById(R.id.day_info_container)
    dayInfoHeader = findViewById(R.id.day_info_header)
    aboutDayButton = findViewById(R.id.day_info_about_btn)

    getSelectedDayInfo(null)
  }

  fun getSelectedDayInfo(day: Day?) {
    if (day == null) {
      nullDayView.visibility = View.VISIBLE
      mainContainer.visibility = View.GONE
    } else {
      mainContainer.visibility = View.VISIBLE
      nullDayView.visibility = View.GONE
      setUpDayInfoFields(day)
    }
  }

  private fun setUpDayInfoFields(day: Day) {
    dayInfoHeader.text = (MonthStruct.values()[day.month].name + "  " + day.day)

    aboutDayButton.setOnClickListener {
      Toast.makeText(context, day.day, Toast.LENGTH_SHORT).show()
    }
  }
}
package pro.papaya.canyo.sportify.model

import java.util.*

class Day {
  val day: Int
  val isCurrentMonth: Boolean
  val isToday: Boolean
  val month: Int
  val year: Int

  constructor(day: Int, isToday: Boolean, month: Int, year: Int) {
    this.day = day
    this.isCurrentMonth = Calendar.getInstance().get(Calendar.MONTH) == month
    this.isToday = isToday
    this.month = month
    this.year = year
  }
}
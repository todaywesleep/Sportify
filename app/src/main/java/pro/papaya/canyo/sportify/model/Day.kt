package pro.papaya.canyo.sportify.model

import java.util.*

class Day {
  val day: Int
  val isCurrentMonth: Boolean
  val isToday: Boolean
  val month: Int
  val year: Int

  constructor(day: Int, isToday: Boolean, isCurrentMonth: Boolean, month: Int, year: Int) {
    this.day = day
    this.isCurrentMonth = isCurrentMonth
    this.isToday = isToday
    this.month = month
    this.year = year
  }
}
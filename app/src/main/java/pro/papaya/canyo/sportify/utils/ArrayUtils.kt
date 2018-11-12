package pro.papaya.canyo.sportify.utils

import pro.papaya.canyo.sportify.model.Day
import java.util.*

class ArrayUtils {
  companion object {
    fun generateDaysArray(calendar: Calendar): ArrayList<Day> {
      val outputArray = arrayListOf<Day>()

      for (i in 1..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
        outputArray.add(Day(
                i,
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR))
        )
      }

      return outputArray
    }

    fun combineDayArrays(arrays: ArrayList<ArrayList<Day>>): ArrayList<Day> {
      val finalArray = arrayListOf<Day>()
      for (array in arrays) {
        for (day in array) {
          finalArray.add(day)
        }
      }
      
      return finalArray
    }
  }
}
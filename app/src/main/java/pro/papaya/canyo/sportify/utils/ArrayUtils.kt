package pro.papaya.canyo.sportify.utils

import pro.papaya.canyo.sportify.model.Day
import java.util.*

class ArrayUtils {
  companion object {
    fun generateDaysArray(from: Int, to: Int, isCurrentMonth: Boolean): ArrayList<Day> {
      val outputArray = arrayListOf<Day>()

      if (from < to) {
        for (i in from..to) {
          outputArray.add(Day(i,
                  isCurrentMonth,
                  Calendar.getInstance().get(Calendar.DAY_OF_MONTH - 1) == i
          ))
        }
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
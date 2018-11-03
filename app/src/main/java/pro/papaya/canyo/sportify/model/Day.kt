package pro.papaya.canyo.sportify.model

class Day {
    val day: Int
    val isCurrentMonth: Boolean
    val isToday: Boolean

    constructor(day: Int, isCurrentMonth: Boolean, isToday: Boolean){
        this.day = day
        this.isCurrentMonth = isCurrentMonth
        this.isToday = isToday
    }
}
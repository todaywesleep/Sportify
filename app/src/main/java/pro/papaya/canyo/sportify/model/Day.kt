package pro.papaya.canyo.sportify.model

class Day {
    val day: Int
    val isCurrentMonth: Boolean

    constructor(day: Int, isCurrentMonth: Boolean){
        this.day = day
        this.isCurrentMonth = isCurrentMonth
    }
}
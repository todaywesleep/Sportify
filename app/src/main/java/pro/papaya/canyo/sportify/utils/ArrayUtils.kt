package pro.papaya.canyo.sportify.utils

class ArrayUtils {
    companion object {
        fun generateArray(from: Int, to: Int): ArrayList<Int>{
            val outputArray = arrayListOf<Int>()

            if (from > to){
                for (i in from..to){
                    outputArray.add(i)
                }
            }

            return outputArray
        }
    }
}
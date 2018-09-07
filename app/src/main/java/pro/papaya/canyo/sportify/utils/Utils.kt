package pro.papaya.canyo.sportify.utils

import android.annotation.SuppressLint
import java.util.*
import java.text.SimpleDateFormat


class Utils {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getRequestDateFormat(from: Date): String {
            val df = SimpleDateFormat("dd-MM-yyyy")
            return df.format(from)
        }
    }
}
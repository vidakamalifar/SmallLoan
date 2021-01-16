package com.inBank.loan.util

class StringUtil {

    companion object{
        @JvmStatic
        fun getMonthString(period: Int): String {
            return "$period months"
        }
        @JvmStatic
        fun getMonthToInt(period: String): Int {
            return Integer.valueOf(period.replace(" months", ""))
        }
    }

}
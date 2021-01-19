package com.inBank.loan.util

import com.inBank.loan.MyApplication.Companion.applicationContext
import com.inBank.loan.R

class StringUtil {

    companion object {

        @JvmStatic
        fun getMonthString(period: Int): String {
            return applicationContext()?.getString(R.string.month_string, period)!!
        }

        @JvmStatic
        fun getMonthToInt(period: String): Int {
            return Integer.valueOf(
                applicationContext()?.getString(R.string.months)?.let {
                    period.replace(
                        it,
                        ""
                    )
                }
            )
        }
    }
}
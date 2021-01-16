package com.inBank.loan.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun dismissKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (null != activity.currentFocus) imm.hideSoftInputFromWindow(
                activity.currentFocus!!.applicationWindowToken, 0
            )
        }

        fun getCurrentDate(): String {
            val sdf = SimpleDateFormat("dd/M/yyyy")
            return sdf.format(Date())
        }
    }

}
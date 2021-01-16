package com.inBank.loan.util


class CreateMonthArray {

    companion object {
        fun getMonths(): MutableList<String> {
            val months: MutableList<String> = mutableListOf()
            for (n in 6 until 61) {
                StringUtil.getMonthString(n).let { months.add(it) }
            }

            return months
        }
    }
}
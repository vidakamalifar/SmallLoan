package com.inBank.loan

import com.inBank.loan.util.StringUtil
import org.junit.Assert
import org.junit.Test

class StringUtilTest {

    @Test
    fun getMonthStringTest() {
        Assert.assertEquals("10 months", StringUtil.getMonthString(10))
    }

    @Test
    fun getMonthToIntTest() {
        Assert.assertEquals(20, StringUtil.getMonthToInt("20 months"))
    }
}
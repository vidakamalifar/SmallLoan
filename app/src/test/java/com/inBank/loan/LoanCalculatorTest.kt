package com.inBank.loan

import com.inBank.loan.util.LoanCalculator
import org.junit.Assert
import org.junit.Test

class LoanCalculatorTest {
    private var NOT_ALLOWED = 1
    private var ALLOWED = 2
    private var AMOUNT_NOT_ALLOWED = 3

    @Test
    fun amountNotAllowedLoanCalculatorTest() {
        LoanCalculator.loanCalculator(period = 6, amount = 2000F, creditModifier = 100F)
        Assert.assertEquals(AMOUNT_NOT_ALLOWED, MyApplication.getIsValidLoanRequest())
    }

    @Test
    fun amountAllowedLoanCalculatorTest() {
        LoanCalculator.loanCalculator(period = 15, amount = 4000F, creditModifier = 300F)
        Assert.assertEquals(ALLOWED, MyApplication.getIsValidLoanRequest())
    }

    @Test
    fun loanNotAllowedLoanCalculatorTest() {
        LoanCalculator.loanCalculator(period = 6, amount = 2000F, creditModifier = 0F)
        Assert.assertEquals(NOT_ALLOWED, MyApplication.getIsValidLoanRequest())
    }
}
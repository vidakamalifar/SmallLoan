package com.inBank.loan

import com.inBank.loan.interfaces.LoanRequestInterface
import com.inBank.loan.model.LoanRequest
import com.inBank.loan.model.LoanStatus
import com.inBank.loan.util.LoanCalculator
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoanCalculatorTest {

    private var loanRequest: LoanRequest? = null
    @Mock
    lateinit var loanRequestInterface: LoanRequestInterface

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loanRequestInterface = Mockito.mock(LoanRequestInterface::class.java)
    }

    @Test
    fun amountNotAllowedLoanCalculatorTest() {
        loanRequest = LoanRequest(6, 10000F, "", 0)
        loanRequest?.let { LoanCalculator.loanCalculator(it, creditModifier = 400F,
            loanRequestInterface = loanRequestInterface) }
        Assert.assertEquals(LoanStatus.LOAN_AMOUNT_NOT_ALLOWED.status, loanRequest!!.status)
    }

    @Test
    fun amountAllowedLoanCalculatorTest() {
        loanRequest = LoanRequest(period = 15, amount = 4000F, message =  "", status = 0)
        loanRequest?.let { LoanCalculator.loanCalculator(it, creditModifier = 300F,
            loanRequestInterface = loanRequestInterface) }

        Assert.assertEquals(LoanStatus.ALLOWED_TO_GET_LOAN.status, loanRequest!!.status)
    }

    @Test
    fun loanNotAllowedLoanCalculatorTest() {
        loanRequest = LoanRequest(period = 6, amount = 2000F, message =  "", status = 0)
        loanRequest?.let { LoanCalculator.loanCalculator(it, creditModifier = 0F,
            loanRequestInterface = loanRequestInterface) }

        Assert.assertEquals(LoanStatus.NOT_ALLOWED_GET_LOAN.status, loanRequest!!.status)

    }
}
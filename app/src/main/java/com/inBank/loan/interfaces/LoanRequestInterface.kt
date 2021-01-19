package com.inBank.loan.interfaces

import com.inBank.loan.model.LoanRequest

interface LoanRequestInterface {
    fun resultCheckingLoan(loanRequest: LoanRequest)
}
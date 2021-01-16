package com.inBank.loan.interfaces.loanactivityinterface

interface LoanPresenterInterface {

    fun checkIdInput(input: String)

    fun calculateLoan(period: Int, amount: Float)
}
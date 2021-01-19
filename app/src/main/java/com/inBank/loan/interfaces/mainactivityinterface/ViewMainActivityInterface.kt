package com.inBank.loan.interfaces.mainactivityinterface

import com.inBank.loan.model.LoanRequest

interface ViewMainActivityInterface {

    fun showValidityAmountResult(validAmount : Boolean,  amount: Int)

    fun goToLoanActivity(loanRequest: LoanRequest)
}
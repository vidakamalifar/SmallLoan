package com.inBank.loan.interfaces.loanactivityinterface

import com.inBank.loan.model.LoanRequest

interface ViewLoanActivityInterface {

    fun showClientIdNumber(clientIdNumber : Long)

    fun showLoanResult(loanRequest: LoanRequest)

    fun showEmptyInputError()

    fun showUserDoesNotExitError()
}
package com.inBank.loan.interfaces.mainactivityinterface

interface MainPresenterInterface {

    fun checkIsValidAmount(amount: Int)

    fun prepareLoanData(amount: Int, period: Int)
}
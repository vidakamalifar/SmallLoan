package com.inBank.loan.presenter

import com.inBank.loan.interfaces.mainactivityinterface.MainPresenterInterface
import com.inBank.loan.interfaces.mainactivityinterface.ViewMainActivityInterface
import com.inBank.loan.model.LoanRequest
import com.inBank.loan.model.LoanTerms

class MainActivityPresenter(private var mViewMainActivityInterface: ViewMainActivityInterface) :
    MainPresenterInterface {

    override fun checkIsValidAmount(amount: Int) {
        when {
            amount < LoanTerms.MINIMUM_LOAN_AMOUNT.value -> {
                mViewMainActivityInterface.showValidityAmountResult(false, amount)
            }
            amount > LoanTerms.MAXIMUM_LOAN_AMOUNT.value -> {
                mViewMainActivityInterface.showValidityAmountResult(false, amount)
            }
            else -> {
                mViewMainActivityInterface.showValidityAmountResult(true, amount)
            }
        }
    }

    override fun prepareLoanData(amount: Int, period: Int) {
        val loanRequest =
            LoanRequest(amount = amount.toFloat(), period = period, message = "", status = 0)
        mViewMainActivityInterface.goToLoanActivity(loanRequest)

    }
}
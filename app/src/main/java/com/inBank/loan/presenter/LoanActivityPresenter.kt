package com.inBank.loan.presenter

import com.inBank.loan.interfaces.loanactivityinterface.LoanPresenterInterface
import com.inBank.loan.interfaces.loanactivityinterface.ViewLoanActivityInterface
import com.inBank.loan.model.Client
import com.inBank.loan.util.LoanCalculator

class LoanActivityPresenter(
    private var mViewLoanActivityInterface: ViewLoanActivityInterface,
    private var clients: List<Client>
) :
    LoanPresenterInterface {

    private var creditModifier: Float? = 0F
    private var isUserExist: Boolean = false

    private fun checkUserId(id: Long) {

        for (client in clients) {
            if (client.id == id) {
                isUserExist = true
                creditModifier = client.creditModifier
                break
            } else {
                isUserExist = false
            }
        }

        if (isUserExist)
            mViewLoanActivityInterface.login(true)
        else
            mViewLoanActivityInterface.login(false)
    }

    override fun checkIdInput(input: String) {
        if (input.isNotEmpty())
            checkUserId((input.toLong()))
        else
            mViewLoanActivityInterface.showInputError()
    }

    override fun calculateLoan(period: Int, amount: Float) {
        val message = creditModifier?.let {
            LoanCalculator.loanCalculator(
                period = period,
                amount = amount,
                creditModifier = it
            )
        }
        message?.let { mViewLoanActivityInterface.showAmountResult(it) }

    }
}
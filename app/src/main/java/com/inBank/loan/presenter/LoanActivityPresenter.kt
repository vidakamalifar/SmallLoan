package com.inBank.loan.presenter

import com.inBank.loan.MyApplication
import com.inBank.loan.interfaces.LoanRequestInterface
import com.inBank.loan.interfaces.loanactivityinterface.LoanPresenterInterface
import com.inBank.loan.interfaces.loanactivityinterface.ViewLoanActivityInterface
import com.inBank.loan.model.Client
import com.inBank.loan.model.LoanRequest
import com.inBank.loan.util.LoanCalculator

class LoanActivityPresenter(
    private var mViewLoanActivityInterface: ViewLoanActivityInterface,
    private var clients: List<Client>,
    private var loanRequest: LoanRequest
) :
    LoanPresenterInterface {

    private var creditModifier: Float? = 0F
    private var clientIdNumber: Long = 0
    private var isUserExist: Boolean = false


    init {
        if (MyApplication.getClientIdNumber() != 0L)
            MyApplication.getClientIdNumber()?.let {
                mViewLoanActivityInterface.showClientIdNumber(
                    it
                )
            }
    }

    override fun processIdInput(input: String) {
        if (input.isNotEmpty())
            checkUserId((input.toLong()))
        else
            mViewLoanActivityInterface.showEmptyInputError()
    }

    private fun checkUserId(id: Long) {

        for (client in clients) {
            if (client.id == id) {
                isUserExist = true
                clientIdNumber = client.id
                creditModifier = client.creditModifier
                break
            } else {
                isUserExist = false
            }
        }

        if (isUserExist) {
            MyApplication.setClientIdNumber(clientIdNumber)
            calculateLoan(loanRequest)
        } else
            mViewLoanActivityInterface.showUserDoesNotExitError()
    }

    private fun calculateLoan(loanRequest: LoanRequest) {
        creditModifier?.let {
            LoanCalculator.loanCalculator(loanRequest, it,
                object : LoanRequestInterface {
                    override fun resultCheckingLoan(loanRequest: LoanRequest) {
                        mViewLoanActivityInterface.showLoanResult(loanRequest)
                    }
                })
        }
    }
}
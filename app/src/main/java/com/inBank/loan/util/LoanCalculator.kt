package com.inBank.loan.util

import android.content.res.Resources
import com.inBank.loan.MyApplication.Companion.applicationContext
import com.inBank.loan.R
import com.inBank.loan.interfaces.LoanRequestInterface
import com.inBank.loan.model.LoanRequest
import com.inBank.loan.model.LoanStatus


class LoanCalculator {

    companion object {
        fun loanCalculator(
            loanRequest: LoanRequest,
            creditModifier: Float,
            loanRequestInterface: LoanRequestInterface?
        ) {
            if (creditModifier > 0) {
                val creditScore = (creditModifier.div(loanRequest.amount)) * loanRequest.period

                loanRequestInterface?.let {
                    checkLoan(
                        it,
                        loanRequest,
                        creditScore,
                        creditModifier
                    )
                }
                return
            }
            loanRequest.status = LoanStatus.NOT_ALLOWED_GET_LOAN.status
            loanRequest.message = applicationContext()?.getString(R.string.not_allowed_get_loan)

            loanRequestInterface?.resultCheckingLoan(loanRequest)
        }

        private fun checkLoan(
            loanRequestInterface: LoanRequestInterface,
            loanRequest: LoanRequest,
            creditScore: Float,
            creditModifier: Float
        ) {
            val maxLoanAmount = (creditModifier * loanRequest.period)
            when {
                creditScore > 1 -> {
                    loanRequest.status = LoanStatus.ALLOWED_TO_GET_LOAN.status
                    loanRequest.message = applicationContext()
                        ?.getString(R.string.allowed_get_more_loan, maxLoanAmount.toLong())

                }

                creditScore == 1F -> {
                    loanRequest.status = LoanStatus.ALLOWED_TO_GET_LOAN.status
                    loanRequest.message =
                        applicationContext()?.getString(R.string.allowed_get_exact_loan)
                }
                else -> {
                    loanRequest.status = LoanStatus.LOAN_AMOUNT_NOT_ALLOWED.status
                    loanRequest.message = applicationContext()
                        ?.getString(R.string.not_allowed_get_exact_loan, maxLoanAmount.toLong())
                }
            }
            loanRequestInterface.resultCheckingLoan(loanRequest)
        }
    }
}
package com.inBank.loan.util

import com.inBank.loan.MyApplication.Companion.applicationContext
import com.inBank.loan.R
import com.inBank.loan.interfaces.LoanRequestInterface
import com.inBank.loan.model.LoanRequest
import com.inBank.loan.model.LoanStatus
import com.inBank.loan.model.LoanTerms


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
            var maxLoanAmount = (creditModifier * loanRequest.period)
            when {
                creditScore > 1 -> {
                    if (maxLoanAmount > LoanTerms.MAXIMUM_LOAN_AMOUNT.value)
                        maxLoanAmount = LoanTerms.MAXIMUM_LOAN_AMOUNT.value.toFloat()

                    loanRequest.status = LoanStatus.ALLOWED_TO_GET_LOAN.status
                    if (maxLoanAmount == loanRequest.amount) {
                        loanRequest.message =
                            applicationContext()?.getString(R.string.allowed_get_exact_loan)
                    } else {
                        loanRequest.message = applicationContext()
                            ?.getString(R.string.allowed_get_more_loan, maxLoanAmount.toLong())

                    }
                }

                creditScore == 1F -> {
                    loanRequest.status = LoanStatus.ALLOWED_TO_GET_LOAN.status
                    loanRequest.message =
                        applicationContext()?.getString(R.string.allowed_get_exact_loan)
                }
                else -> {
                    if (maxLoanAmount < LoanTerms.MINIMUM_LOAN_AMOUNT.value) {
                        maxLoanAmount = LoanTerms.MINIMUM_LOAN_AMOUNT.value.toFloat()
                        val period = maxLoanAmount.div(creditModifier)

                        loanRequest.message = applicationContext()
                            ?.getString(R.string.not_allowed_get_loan_caused_period, period.toLong())

                    } else {
                        loanRequest.message = applicationContext()
                            ?.getString(R.string.not_allowed_get_exact_loan, maxLoanAmount.toLong())
                    }

                    loanRequest.status = LoanStatus.LOAN_AMOUNT_NOT_ALLOWED.status

                }
            }
            loanRequestInterface.resultCheckingLoan(loanRequest)
        }
    }
}
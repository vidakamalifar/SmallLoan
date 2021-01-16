package com.inBank.loan.util

import com.inBank.loan.MyApplication
import com.inBank.loan.util.Constant.ALLOWED
import com.inBank.loan.util.Constant.AMOUNT_NOT_ALLOWED
import com.inBank.loan.util.Constant.NOT_ALLOWED


class LoanCalculator {
    
    companion object {

        fun loanCalculator(period: Int, amount: Float, creditModifier: Float): String {
            if (creditModifier > 0) {
                val creditScore = (creditModifier.div(amount)) * period

                return checkLoan(period, amount, creditScore)
            }
            MyApplication.setIsValidLoanRequest(NOT_ALLOWED)
            return "The credit scoring system shows that you have debt in banking system. While you have debt in other banks," +
                    " you are not allowed to request a new loan in the banking system."
        }

        private fun checkLoan(period: Int, amount: Float, creditScore: Float): String {
            return when {
                creditScore > 1 -> {
                    val creditModifier = (creditScore * amount).div(period)

                    MyApplication.setIsValidLoanRequest(ALLOWED)

                    "As we checked your credit score, you are allowed to request this loan. Rather than that, the"+
                    " the maximum amount of loan that you are able to request is:\n" +
                            "€$creditModifier\n" +
                            "Do you want to update your request or submit the current request?"

                }

                creditScore == 1F -> {
                    MyApplication.setIsValidLoanRequest(ALLOWED)

                    "As we checked your credit score, you are allowed to request this loan"

                }
                else -> {
                    val creditModifier = (creditScore * amount).div(period)

                    MyApplication.setIsValidLoanRequest(AMOUNT_NOT_ALLOWED)

                    "As we checked your credit score, you are NOT allowed for this request. " +
                            "By considering your credit score in the banking system, the maximum amount for you to have a loan is:\n " +
                            "€$creditModifier\n" +
                            "You can update or cancel your request."
                }
            }

        }
    }
}
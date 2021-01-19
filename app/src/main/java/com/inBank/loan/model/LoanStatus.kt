package com.inBank.loan.model

enum class LoanStatus(val status: Int) {
    NOT_ALLOWED_GET_LOAN(1),
    ALLOWED_TO_GET_LOAN(2),
    LOAN_AMOUNT_NOT_ALLOWED(3)
}
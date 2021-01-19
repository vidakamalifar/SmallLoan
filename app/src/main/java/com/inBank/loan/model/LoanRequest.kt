package com.inBank.loan.model

import java.io.Serializable

data class LoanRequest (
    var period: Int = 0,
    var amount: Float = 0F,
    var message : String? = null,
    var status : Int = 0
) : Serializable
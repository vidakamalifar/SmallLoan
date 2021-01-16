package com.inBank.loan

import android.app.Application
import android.content.Context
import com.inBank.loan.model.Client

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        createClient()
    }

    fun createClient(): List<Client> {
        return setOf(
            Client(id = 49002010965, creditModifier = 0F),
            Client(id = 49002010976, creditModifier = 100F),
            Client(id = 49002010987, creditModifier = 300F),
            Client(id = 49002010998, creditModifier = 1000F)
        ).toList()
    }

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        var isValidLoan: Int = 0

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        fun setIsValidLoanRequest(isValid: Int) {
            isValidLoan = isValid
        }

        fun getIsValidLoanRequest(): Int {
            return isValidLoan
        }
    }
}
package com.inBank.loan.presenter

import com.inBank.loan.interfaces.mainactivityinterface.MainPresenterInterface
import com.inBank.loan.interfaces.mainactivityinterface.ViewMainActivityInterface

class MainActivityPresenter(private var mViewMainActivityInterface: ViewMainActivityInterface) :
    MainPresenterInterface {

    override fun checkAmount(amount: Int) {
        when {
            amount < 2000 -> {
                mViewMainActivityInterface.showAmountResult(false, amount)
            }
            amount > 10000 -> {
                mViewMainActivityInterface.showAmountResult(false, amount)
            }
            else -> {
                mViewMainActivityInterface.showAmountResult(true, amount)
            }
        }
    }
}
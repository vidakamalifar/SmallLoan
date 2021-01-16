package com.inBank.loan.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.inBank.loan.MyApplication
import com.inBank.loan.R
import com.inBank.loan.databinding.ActivityLoanBinding
import com.inBank.loan.dialog.LoanResultDialog
import com.inBank.loan.interfaces.LoanResultDialogInterface
import com.inBank.loan.interfaces.loanactivityinterface.LoanPresenterInterface
import com.inBank.loan.interfaces.loanactivityinterface.ViewLoanActivityInterface
import com.inBank.loan.model.Client
import com.inBank.loan.presenter.LoanActivityPresenter
import com.inBank.loan.util.Constant.AMOUNT_VALUE
import com.inBank.loan.util.Constant.PERIOD_VALUE
import com.inBank.loan.util.Utils
import kotlinx.android.synthetic.main.activity_loan.*

class LoanActivity : AppCompatActivity(),
    ViewLoanActivityInterface, LoanResultDialogInterface {

    //data binding
    private lateinit var binding: ActivityLoanBinding

    //initial presenter
    private lateinit var loanActivityInterface: LoanPresenterInterface

    private val myApplication: MyApplication = MyApplication.applicationContext() as MyApplication

    //load static user
    private val clients: List<Client> = myApplication.createClient()

    private var amount: Float = 0F

    private var period: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_loan)

        loanActivityInterface = LoanActivityPresenter(this, clients)

        //actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        //get value from intent
        amount = intent.getIntExtra(AMOUNT_VALUE, 0).toFloat()
        period = intent.getIntExtra(PERIOD_VALUE, 0)

        setListener()

    }

    private fun setListener() {
        binding.btnLogin.setOnClickListener {

            //close keyboard
            Utils.dismissKeyboard(this@LoanActivity)

            loanActivityInterface.checkIdInput(binding.etIdNumber.text.toString())
        }
    }

    override fun showAmountResult(message: String) {
        LoanResultDialog(this, message, period, amount)
    }

    override fun showInputError() {
        txtInputLayout.error = getString(R.string.empty_id_error)
    }

    override fun login(isSuccess: Boolean) {
        if (isSuccess) {
            loanActivityInterface.calculateLoan(period, amount)
        } else {
            txtInputLayout.error = getString(R.string.invalid_id_error)
        }
    }

    override fun purchasedLoan() {
        Toast.makeText(
            applicationContext,
            getString(R.string.loan_purchased_result), Toast.LENGTH_LONG
        ).show()
        onBackPressed()
    }

    override fun rejectLoan() {
        onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
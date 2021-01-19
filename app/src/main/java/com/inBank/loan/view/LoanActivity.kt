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
import com.inBank.loan.model.LoanRequest
import com.inBank.loan.presenter.LoanActivityPresenter
import com.inBank.loan.util.Constant.LOAN_OBJECT_VALUE
import com.inBank.loan.util.Utils
import kotlinx.android.synthetic.main.activity_loan.*

class LoanActivity : AppCompatActivity(),
    ViewLoanActivityInterface {

    //data binding
    private lateinit var binding: ActivityLoanBinding

    //initial presenter
    private lateinit var loanActivityInterface: LoanPresenterInterface

    private val myApplication: MyApplication = MyApplication.applicationContext() as MyApplication

    //load static user
    private val clients: List<Client> = myApplication.createClient()

    private lateinit var loanRequest: LoanRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_loan)

        //get value from intent
        loanRequest = intent.getSerializableExtra(LOAN_OBJECT_VALUE) as LoanRequest

        //initial presenter
        loanActivityInterface = LoanActivityPresenter(this, clients, loanRequest)

        //actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""


        setListener()

    }

    private fun setListener() {
        binding.btnLogin.setOnClickListener {

            //close keyboard
            Utils.dismissKeyboard(this@LoanActivity)

            loanActivityInterface.processIdInput(binding.etIdNumber.text.toString())
        }
    }

    override fun showClientIdNumber(clientIdNumber: Long) {
        binding.clientIdNumber = clientIdNumber
    }

    override fun showLoanResult(loanRequest: LoanRequest) {
        LoanResultDialog(this, loanRequest, object : LoanResultDialogInterface {
            override fun purchasedLoan() {
                Toast.makeText(
                    this@LoanActivity,
                    getString(R.string.loan_purchased_result), Toast.LENGTH_LONG
                ).show()

                onBackPressed()
            }

            override fun rejectLoan() {
                onBackPressed()
            }
        })
    }

    override fun showEmptyInputError() {
        txtInputLayout.error = getString(R.string.empty_id_error)
    }

    override fun showUserDoesNotExitError() {
        txtInputLayout.error = getString(R.string.invalid_id_error)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
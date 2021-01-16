package com.inBank.loan.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.inBank.loan.R
import com.inBank.loan.databinding.ActivityMainBinding
import com.inBank.loan.dialog.ChooseMonthDialog
import com.inBank.loan.interfaces.mainactivityinterface.MainPresenterInterface
import com.inBank.loan.interfaces.PeriodDialogInterface
import com.inBank.loan.interfaces.mainactivityinterface.ViewMainActivityInterface
import com.inBank.loan.presenter.MainActivityPresenter
import com.inBank.loan.util.Constant.AMOUNT_VALUE
import com.inBank.loan.util.Constant.PERIOD_VALUE
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PeriodDialogInterface,
    ViewMainActivityInterface {

    //data binding
    private lateinit var binding: ActivityMainBinding

    //presenter
    private var mainActivityInterface: MainPresenterInterface? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set drawable icon
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.inbank_logo__purple)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityInterface = MainActivityPresenter(this)

        //initial min period
        binding.period = 6

        binding.chooseMonthDialog = this

        binding.etAmount.addTextChangedListener(textWatcher)

        //initial min amount
        binding.amount = 2000

        setListener()
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s != null && s.toString().isNotEmpty()) {
                mainActivityInterface?.checkAmount(Integer.parseInt(s.toString()))
            }
        }
    }

    private fun setListener() {
        binding.btnSubmit.setOnClickListener {
            goToLoanActivity()
        }

        amountSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, amount: Int, b: Boolean) {
                // Display the current progress of SeekBar
                binding.amount = amount
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })
        periodSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, period: Int, b: Boolean) {
                // Display the current progress of SeekBar
                binding.period = period
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })
    }

    private fun goToLoanActivity() {
        Intent(this, LoanActivity::class.java).also {
            it.putExtra(AMOUNT_VALUE, binding.amount)
            it.putExtra(PERIOD_VALUE, binding.period)
            startActivity(it)
        }

    }

    override fun inflateMonthDialog() {
        ChooseMonthDialog(this)
    }

    override fun setMonth(month: Int) {
        binding.period = month
    }

    override fun showAmountResult(validAmount: Boolean, amount: Int) {
        if (validAmount) {
            binding.btnSubmit.isEnabled = true
            binding.amount = amount
            binding.etAmount.setBackgroundResource(R.drawable.border_rounded_edit_text)
        } else {
            binding.btnSubmit.isEnabled = false
            binding.etAmount.setBackgroundResource(R.drawable.border_rounded_error_edit_text)
        }
    }
}
package com.inBank.loan.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.inBank.loan.MyApplication
import com.inBank.loan.R
import com.inBank.loan.databinding.DialogLoanResultBinding
import com.inBank.loan.interfaces.LoanResultDialogInterface
import com.inBank.loan.util.Constant.AMOUNT_NOT_ALLOWED
import com.inBank.loan.util.Constant.NOT_ALLOWED
import com.inBank.loan.util.StringUtil
import com.inBank.loan.util.Utils


class LoanResultDialog(
    private val context: Context,
    private var loanResultMessage: String,
    private var period: Int,
    private var amount: Float
) : BaseDialog(context) {

    private lateinit var binding: DialogLoanResultBinding

    init {
        showDialog()
    }

    private fun showDialog() {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_loan_result,
            null,
            false
        )
        setParentView(binding.root)

        setData()

        show()

        setInterface()

    }

    private fun setData() {
        binding.message = loanResultMessage
        binding.period = StringUtil.getMonthString(period)
        binding.amount = "$amount EUR"
        binding.date = Utils.getCurrentDate()

        if (MyApplication.getIsValidLoanRequest() == NOT_ALLOWED) {
            binding.btnAcceptLoan.visibility = View.GONE
            binding.btnRejectLoan.visibility = View.GONE
            binding.btnBackToHome.visibility = View.VISIBLE
            binding.ivIcon.setImageResource(R.drawable.x_circle_fill)

        } else if (MyApplication.getIsValidLoanRequest() == AMOUNT_NOT_ALLOWED) {
            binding.btnAcceptLoan.isEnabled = false
            binding.btnAcceptLoan.alpha = 0.5F
            binding.ivIcon.setImageResource(R.drawable.x_circle_fill)
        }
    }

    private fun setInterface() {
        val loanResultDialogInterface: LoanResultDialogInterface =
            context as LoanResultDialogInterface

        binding.btnAcceptLoan.setOnClickListener {
            loanResultDialogInterface.purchasedLoan()
            dismiss()
        }
        binding.btnRejectLoan.setOnClickListener {
            loanResultDialogInterface.rejectLoan()
            dismiss()
        }
        binding.btnBackToHome.setOnClickListener {
            loanResultDialogInterface.rejectLoan()
            dismiss()
        }
    }

    companion object {
        private const val TAG = "LoanResultDialog"
    }
}

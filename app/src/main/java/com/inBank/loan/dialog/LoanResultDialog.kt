package com.inBank.loan.dialog

import android.content.Context
import android.view.*
import androidx.databinding.DataBindingUtil
import com.inBank.loan.R
import com.inBank.loan.databinding.DialogLoanResultBinding
import com.inBank.loan.interfaces.LoanResultDialogInterface
import com.inBank.loan.model.LoanRequest
import com.inBank.loan.model.LoanStatus
import com.inBank.loan.util.StringUtil
import com.inBank.loan.util.Utils


class LoanResultDialog(
    private val context: Context,
    private var loanRequest: LoanRequest,
    private var loanResultDialogInterface: LoanResultDialogInterface
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

        //set full screen and style to dialog
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.gravity = Gravity.BOTTOM
        dialog.setCancelable(false)
        lp.windowAnimations = R.style.DialogLoanResult
        dialog.window!!.attributes = lp


        setInterface()

    }

    private fun setData() {
        binding.message = loanRequest.message
        binding.period = StringUtil.getMonthString(loanRequest.period)
        binding.amount = "${loanRequest.amount}" + context.resources.getString(R.string.eur)
//
        binding.date = Utils.getCurrentDate()

        if (loanRequest.status == LoanStatus.NOT_ALLOWED_GET_LOAN.status) {
            binding.btnAcceptLoan.visibility = View.GONE
            binding.btnRejectLoan.visibility = View.GONE
            binding.btnBackToHome.visibility = View.VISIBLE
            binding.ivIcon.setImageResource(R.drawable.x_circle_fill)

        } else if (loanRequest.status == LoanStatus.LOAN_AMOUNT_NOT_ALLOWED.status) {
            binding.btnAcceptLoan.isEnabled = false
            binding.btnAcceptLoan.alpha = 0.5F
            binding.ivIcon.setImageResource(R.drawable.x_circle_fill)
        }
    }

    private fun setInterface() {
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

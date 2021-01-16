package com.inBank.loan.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.inBank.loan.R
import com.inBank.loan.databinding.DialogChooseMonthBinding
import com.inBank.loan.interfaces.PeriodDialogInterface
import com.inBank.loan.util.CreateMonthArray
import com.inBank.loan.util.StringUtil


class ChooseMonthDialog(private val context: Context) : BaseDialog(context) {

    private lateinit var binding: DialogChooseMonthBinding

    private lateinit var monthsList: List<String>

    init {
        showDialog()
    }

    private fun showDialog() {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_choose_month,
            null,
            false
        )
        setParentView(binding.root)

        show()

        setupAdapter()
    }

    private fun setupAdapter() {
        monthsList = CreateMonthArray.getMonths()

        val arrayAdapter =
            ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, monthsList)

        binding.listView.adapter = arrayAdapter

        binding.listView.setOnItemClickListener { adapterView, view, position: Int, id: Long ->

            val periodDialogInterface: PeriodDialogInterface = context as PeriodDialogInterface
            periodDialogInterface.setMonth(StringUtil.getMonthToInt(monthsList[position]))
            dismiss()
        }
    }

    companion object {
        private const val TAG = "ChooseMonthDialog"
    }
}

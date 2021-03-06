package br.com.danielsan.notafiscalcidada.shared.pickers

import android.widget.DatePicker
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*

/**
 * Created by saturo on 19/08/17.
 */
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var listener: DatePickerDialog.OnDateSetListener?  = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as DatePickerDialog.OnDateSetListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        listener?.onDateSet(view,year,month, day)
    }
}
package br.com.danielsan.notafiscalcidada.features.invoices

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityListBinding
import br.com.danielsan.notafiscalcidada.databinding.DialogInvoicesFilterBinding
import br.com.danielsan.notafiscalcidada.domain.invoice.Invoice
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject

/**
 * Created by daniel on 02/10/17.
 */
class InvoicesActivity : AnalyticsActivity(), InvoicesContract {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, InvoicesActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: InvoicesPresenter
    private val adapter by lazy { InvoiceAdapter(presenter) }
    private val semesterOfYear by lazy { getString(R.string.semester_of_year) }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.toolbar?.apply {
            title.text = getText(R.string.invoices)
            back.setOnClickListener { finish() }
            action.visibility = View.VISIBLE
            action.setImageResource(R.drawable.ic_filter_list_curious_blue_24dp)
            action.setOnClickListener { showFilter() }
        }
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun clearInvoices() {
        adapter.list.clear()
        adapter.notifyDataSetChanged()
    }

    override fun showInvoices(invoiceList: List<Invoice>) {
        adapter.list.addAll(invoiceList)
        adapter.notifyDataSetChanged()
    }

    private fun showFilter() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val dialogBinding = DialogInvoicesFilterBinding.inflate(layoutInflater, null, false).apply {
            month.maxValue = 12
            month.minValue = 1
            month.value = currentMonth + 1
            month.wrapSelectorWheel = false
            year.minValue = 2000
            year.maxValue = currentYear
            year.value = year.maxValue
            year.wrapSelectorWheel = false

            semester.minValue = 1
            semester.maxValue = (currentYear - 2000) * 2
            semester.maxValue += if (currentMonth <= 5) 1 else 2
            semester.value = semester.maxValue
            semester.wrapSelectorWheel = false
            semester.setFormatter { semesterOfYear.format(2 - (it % 2), 2000 + ((it - 1) / 2)) }
        }
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.filter))
                .setView(dialogBinding.root)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    dialogBinding.apply {
                        if (dialogBinding.semesterRadio.isChecked) {
                            logEvent(FirebaseAnalytics.Event.SEARCH, mapOf(
                                    FirebaseAnalytics.Param.SEARCH_TERM to semester.value.toString(),
                                    "date" to Date().toString()
                            ))
                            presenter.onFilterSelected(semester.value)
                        } else if (dialogBinding.monthRadio.isChecked) {
                            logEvent(FirebaseAnalytics.Event.SEARCH, mapOf(
                                    FirebaseAnalytics.Param.SEARCH_TERM to semester.value.toString(),
                                    "date" to Date().toString()
                            ))
                            presenter.onFilterSelected(month.value, year.value)
                        }
                    }
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
    }

}

package br.com.danielsan.notafiscalcidada.features.report

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.DatePicker
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivitySignupReportBinding
import br.com.danielsan.notafiscalcidada.domain.report.Report
import br.com.danielsan.notafiscalcidada.features.report.confirmation.ConfirmationActivity
import br.com.danielsan.notafiscalcidada.shared.pickers.DatePickerFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by saturo on 18/08/17.
 */
class SignUpReportActivity : AnalyticsActivity(), SignUpReportContract, DatePickerDialog.OnDateSetListener {

    companion object {
        const val EXTRA_REPORT = "report"
        fun createIntent(context: Context, report: Report? = null): Intent {
            return Intent(context, SignUpReportActivity::class.java)
                    .putExtra(EXTRA_REPORT, report)
        }
    }

    @Inject
    lateinit var presenter: SignUpReportPresenter
    private val signUpReport by lazy { intent?.getParcelableExtra(EXTRA_REPORT) ?: Report(value = 0.0) }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivitySignupReportBinding>(this, R.layout.activity_signup_report)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.apply {
            this.presenter = this@SignUpReportActivity.presenter
            this.reportViewModel = ReportViewModel(signUpReport)
        }
        setUpView()
        presenter.attachView(this)

        logEvent("report_begin", mapOf())
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun navigateToConfirmation() {
        logEvent("report_complete", mapOf())
        startActivity(ConfirmationActivity.createIntent(this))
        finish()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val dd = if (day < 10) "0$day" else "$day"
        val mm = if (month < 10) "0$month" else "$month"
        val date = "$dd/$mm/$year"
        binding.editDate.text = date
    }

    private fun setUpView() {
        binding.toolbar?.apply {
            title.text = getString(R.string.report_title)
            back.setOnClickListener { finish() }
        }

        binding.editDate.setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(supportFragmentManager, "datePicker")
        }

        binding.reportTypeGroup.setOnCheckedChangeListener { radioGroup, _ ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.radio_value -> binding.reportViewModel?.reportType = "1"
                else -> binding.reportViewModel?.reportType = "2"
            }
        }
    }

}

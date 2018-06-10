package br.com.danielsan.notafiscalcidada.features.report.list

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityReportsBinding
import br.com.danielsan.notafiscalcidada.domain.report.Report
import br.com.danielsan.notafiscalcidada.features.report.SignUpReportActivity
import br.com.danielsan.notafiscalcidada.shared.decorator.SimpleDividerItemDecoration
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by saturo on 20/08/17.
 */
class ReportsActivity : AnalyticsActivity(), ReportsContract {
    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ReportsActivity::class.java)
        }
    }

    private lateinit var reportsAdapter: ReportsAdapter
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityReportsBinding>(this, R.layout.activity_reports)
    }
    @Inject
    lateinit var presenter: ReportsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.presenter = presenter
        binding.toolbar?.apply {
            title.text = getString(R.string.your_reports_title)
            back.setOnClickListener{ onBackPressed() }
        }
        presenter.attachView(this)
    }

    private fun setUpList() {
        binding.list.apply {
            adapter = reportsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(SimpleDividerItemDecoration(
                    ContextCompat.getDrawable(context, R.drawable.shape_line_divider)))
        }
    }

    override fun setList(reportsList: MutableList<Report>?) {
        reportsList?.let {
            if (!it.isEmpty()) {
                reportsAdapter = ReportsAdapter(reportsList, presenter)
                setUpList()
            }
        }
    }

    override fun navigateToEditReport(report: Report) {
        startActivity(SignUpReportActivity.createIntent(this, report))
    }

    override fun navigateToCreateReport() {
        startActivity(SignUpReportActivity.createIntent(this))
    }

}

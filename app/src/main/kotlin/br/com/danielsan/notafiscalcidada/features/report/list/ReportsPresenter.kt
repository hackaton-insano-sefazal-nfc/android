package br.com.danielsan.notafiscalcidada.features.report.list

import br.com.danielsan.notafiscalcidada.domain.report.Report
import br.com.danielsan.notafiscalcidada.domain.report.ReportListRequest
import br.com.danielsan.notafiscalcidada.domain.report.ReportRepository
import br.com.danielsan.notafiscalcidada.extensions.observeOnMainUi
import br.com.danielsan.notafiscalcidada.extensions.showErrorMessage
import br.com.danielsan.notafiscalcidada.extensions.subscribeOnIo
import br.com.danielsan.notafiscalcidada.shared.Presenter
import java.text.DateFormat
import java.util.*

/**
 * Created by saturo on 20/08/17.
 */
class ReportsPresenter(private val reportRepository: ReportRepository) : Presenter<ReportsContract>() {

    override fun attachView(view: ReportsContract) {
        super.attachView(view)
        loadReports()
    }

    fun onClickCreateReport() {
        view?.navigateToCreateReport()
    }

    fun onClickEdit(report: Report) {
        view?.navigateToEditReport(report)
    }

    private fun loadReports() {
        reportRepository.getReports(createRequest())
               .subscribeOnIo()
               .observeOnMainUi()
               .subscribe({
                   view?.setList(it)
               }, {
                   showErrorMessage(it)
               })
    }

    private fun createRequest(): ReportListRequest {
        val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale("pt", "BR"))
        val calendar = Calendar.getInstance()
        val endDate = dateFormat.format(calendar.time)
        calendar.set(Calendar.DAY_OF_YEAR, 1)
        return ReportListRequest(
                cnpj = "",
                endDate = endDate,
                startDate = dateFormat.format(calendar.time),
                reportType = "1"
        )
    }

}

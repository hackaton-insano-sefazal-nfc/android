package br.com.danielsan.notafiscalcidada.features.report

import br.com.danielsan.notafiscalcidada.domain.report.Report

/**
 * Created by saturo on 19/08/17.
 */
class ReportViewModel(private val report: Report) {
    var cnpjIssuer: String = report.cnpjIssuer
    var cnpjOrCpf: String? = report.cnpjRecipient ?: report.cpfRecipient
    var reportText: String = report.report
    var dateOfIssue: String = report.dateOfIssue
    var reportType: String = report.reportType
    var value: String = report.value.toString()

    fun getReport(): Report {
        return report.apply {
            cnpjIssuer = this@ReportViewModel.cnpjIssuer
            cnpjRecipient = keepOnlyNumbers(cnpjOrCpf)
            cpfRecipient = keepOnlyNumbers(cnpjOrCpf)
            report = this@ReportViewModel.reportText
            dateOfIssue = this@ReportViewModel.dateOfIssue
            reportType = this@ReportViewModel.reportType
            value = this@ReportViewModel.value.toDouble()
        }
    }

    private fun keepOnlyNumbers(text: String?): String? {
        return if (text != null && text.length > 14) {
            text.replace("[\\D]".toRegex(), "")
        } else {
            null
        }
    }

}

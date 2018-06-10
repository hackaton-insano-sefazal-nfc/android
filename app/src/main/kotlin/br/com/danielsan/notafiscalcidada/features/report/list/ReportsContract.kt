package br.com.danielsan.notafiscalcidada.features.report.list

import br.com.danielsan.notafiscalcidada.base.BaseView
import br.com.danielsan.notafiscalcidada.domain.report.Report

/**
 * Created by saturo on 20/08/17.
 */
interface ReportsContract : BaseView {

    fun setList(reportsList: MutableList<Report>?)

    fun navigateToEditReport(report: Report)

    fun navigateToCreateReport()

}

package br.com.danielsan.notafiscalcidada.features.report

import br.com.danielsan.notafiscalcidada.domain.report.Report
import br.com.danielsan.notafiscalcidada.domain.report.ReportRepository
import br.com.danielsan.notafiscalcidada.extensions.dismissLoading
import br.com.danielsan.notafiscalcidada.extensions.observeOnMainUi
import br.com.danielsan.notafiscalcidada.extensions.showErrorMessage
import br.com.danielsan.notafiscalcidada.extensions.showLoading
import br.com.danielsan.notafiscalcidada.extensions.subscribeOnIo
import br.com.danielsan.notafiscalcidada.shared.Presenter

/**
 * Created by saturo on 18/08/17.
 */
class SignUpReportPresenter(
    private val reportRepository: ReportRepository
) : Presenter<SignUpReportContract>() {

    fun onClickReport(report: Report?) {
        if (report == null) return
        reportRepository.signUpReport(report)
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({ view?.navigateToConfirmation() }, { showErrorMessage(it) })
    }

}

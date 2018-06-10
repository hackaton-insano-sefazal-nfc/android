package br.com.danielsan.notafiscalcidada.domain.report

import io.reactivex.Single

/**
 * Created by daniel on 26/09/17.
 */
class FakeReportRepositoryImpl : ReportRepository {

    override fun signUpReport(report: Report): Single<String> {
        return Single.just("")
    }

    override fun editReport(report: Report): Single<String> {
        return Single.just("")
    }

    override fun getReports(reportListRequest: ReportListRequest): Single<MutableList<Report>> {
        return Single.just(getFakeReports())
    }

    private fun getFakeReports(): MutableList<Report> = (0..5).map { Report(id = it, cNF = it) }.toMutableList()

}

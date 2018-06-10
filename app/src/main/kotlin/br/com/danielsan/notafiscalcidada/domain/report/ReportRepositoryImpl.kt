package br.com.danielsan.notafiscalcidada.domain.report

import br.com.danielsan.notafiscalcidada.extensions.socketTimeoutRetry
import io.reactivex.Single
import retrofit2.Retrofit

/**
 * Created by saturo on 18/08/17.
 */
class ReportRepositoryImpl(private val retrofit: Retrofit) : ReportRepository {

    private val api by lazy { retrofit .create(ReportRepository::class.java) }

    override fun signUpReport(report: Report): Single<String> {
        return api.signUpReport(report)
                .socketTimeoutRetry()
    }

    override fun editReport(report: Report): Single<String> {
        return api.editReport(report)
                .socketTimeoutRetry()
    }

    override fun getReports(reportListRequest: ReportListRequest): Single<MutableList<Report>> {
        return api.getReports(reportListRequest)
                .socketTimeoutRetry()
    }

}

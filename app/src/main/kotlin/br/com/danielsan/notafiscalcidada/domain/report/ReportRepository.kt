package br.com.danielsan.notafiscalcidada.domain.report

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by saturo on 18/08/17.
 */
interface ReportRepository {

    @POST("sfz-nfcidada-api/api/public/denuncia/incluir")
    fun signUpReport(@Body report: Report): Single<String>

    @POST("sfz-nfcidada-api/api/public/denuncia/alterar")
    fun editReport(@Body report: Report): Single<String>

    @POST("sfz-nfcidada-api/api/public/denuncia")
    fun getReports(@Body reportListRequest: ReportListRequest): Single<MutableList<Report>>

}

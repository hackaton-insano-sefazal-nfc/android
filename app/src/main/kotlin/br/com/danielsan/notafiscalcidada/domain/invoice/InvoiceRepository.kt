package br.com.danielsan.notafiscalcidada.domain.invoice

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by daniel on 18/08/17.
 */
interface InvoiceRepository {

    @GET("sfz-nfcidada-api/api/public/notas/{period}")
    fun invoices(@Path("period") period: String): Single<List<Invoice>>

}
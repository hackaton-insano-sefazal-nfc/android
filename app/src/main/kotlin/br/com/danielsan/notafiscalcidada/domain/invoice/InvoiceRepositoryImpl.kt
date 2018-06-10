package br.com.danielsan.notafiscalcidada.domain.invoice

import br.com.danielsan.notafiscalcidada.extensions.socketTimeoutRetry
import io.reactivex.Single
import retrofit2.Retrofit

/**
 * Created by daniel on 18/08/17.
 */
class InvoiceRepositoryImpl(private val retrofit: Retrofit) : InvoiceRepository {

    private val api by lazy { retrofit.create(InvoiceRepository::class.java) }

    override fun invoices(period: String): Single<List<Invoice>> {
        return api.invoices(period)
                .socketTimeoutRetry()
    }

}
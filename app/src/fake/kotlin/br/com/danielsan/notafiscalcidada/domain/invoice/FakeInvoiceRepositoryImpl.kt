package br.com.danielsan.notafiscalcidada.domain.invoice

import io.reactivex.Single
import java.util.*

/**
 * Created by daniel on 18/08/17.
 */
class FakeInvoiceRepositoryImpl : InvoiceRepository {

    override fun invoices(period: String): Single<List<Invoice>> {
        val invoice = Invoice(
                issueDate = Date(),
                number = "54328974895",
                type = "01",
                totalValue = 42f,
                taxpayer = "Casa das Vendas",
                cancellationReason = "Test",
                issuerNumber = "2394234673246",
                creditValue = 0.3f
        )
        return Single.just(mutableListOf(
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice,
                invoice
        ))
    }

}
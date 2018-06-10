package br.com.danielsan.notafiscalcidada.features.invoices

import br.com.danielsan.notafiscalcidada.base.BaseView
import br.com.danielsan.notafiscalcidada.domain.invoice.Invoice

/**
 * Created by daniel on 01/10/17.
 */
interface InvoicesContract : BaseView {

    fun clearInvoices()

    fun showInvoices(invoiceList: List<Invoice>)

}

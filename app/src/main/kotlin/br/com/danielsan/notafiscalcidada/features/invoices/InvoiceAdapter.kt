package br.com.danielsan.notafiscalcidada.features.invoices

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.danielsan.notafiscalcidada.databinding.ItemInvoiceBinding
import br.com.danielsan.notafiscalcidada.domain.invoice.Invoice
import br.com.danielsan.notafiscalcidada.shared.recyclerview.BasicListAdapter

/**
 * Created by daniel on 02/10/17.
 */
class InvoiceAdapter(private val presenter: InvoicesPresenter) : BasicListAdapter<Invoice, InvoiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        return InvoiceViewHolder(ItemInvoiceBinding.inflate(LayoutInflater.from(parent.context), parent, false), presenter)
    }

}

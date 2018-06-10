package br.com.danielsan.notafiscalcidada.features.invoices

import android.text.TextUtils
import br.com.danielsan.notafiscalcidada.Constants
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.databinding.ItemInvoiceBinding
import br.com.danielsan.notafiscalcidada.domain.invoice.Invoice
import br.com.danielsan.notafiscalcidada.shared.recyclerview.BasicViewHolder
import br.com.jansenfelipe.androidmask.Utils

/**
 * Created by daniel on 02/10/17.
 */
class InvoiceViewHolder(
        private val binding: ItemInvoiceBinding,
        private val presenter: InvoicesPresenter
) : BasicViewHolder<Invoice>(binding.root) {

    private var invoice: Invoice? = null

    init {
        binding.root.setOnClickListener { invoice?.let { presenter.onClickInvoice(it) } }
    }

    override fun bind(obj: Invoice) {
        invoice = obj
        binding.apply {
            title.text = root.context.getString(R.string.value_and_credit,
                    Constants.CURRENCY_FORMAT.format(obj.totalValue),
                    Constants.CURRENCY_FORMAT.format(obj.creditValue)
            )
            date.text = Constants.DATE_FORMAT.format(obj.issueDate)
            description.text = StringBuilder()
                    .append("Tipo: ")
                    .append(obj.type)
                    .append("\nNúmero: ")
                    .append(obj.number)
                    .append("\nEmitente: ")
                    .append(obj.taxpayer)
                    .append(" - ")
                    .append(Utils.mask(Constants.CNPJ_MASK, obj.issuerNumber))
                    .append(getCancellationReason(obj.cancellationReason))
                    .toString()

            executePendingBindings()
        }
    }

    private fun getCancellationReason(cancellationReason: String?): String {
        return if (TextUtils.isEmpty(cancellationReason)) {
            ""
        } else {
            "\nAnulado: $cancellationReason"
        }
    }

}

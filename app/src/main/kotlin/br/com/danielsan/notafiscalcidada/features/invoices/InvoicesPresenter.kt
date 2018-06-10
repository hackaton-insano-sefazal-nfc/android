package br.com.danielsan.notafiscalcidada.features.invoices

import br.com.danielsan.notafiscalcidada.data.persistence.Preferences
import br.com.danielsan.notafiscalcidada.data.persistence.PreferencesKey
import br.com.danielsan.notafiscalcidada.data.persistence.getString
import br.com.danielsan.notafiscalcidada.domain.invoice.Invoice
import br.com.danielsan.notafiscalcidada.domain.invoice.InvoiceRepository
import br.com.danielsan.notafiscalcidada.extensions.dismissLoading
import br.com.danielsan.notafiscalcidada.extensions.observeOnMainUi
import br.com.danielsan.notafiscalcidada.extensions.showErrorMessage
import br.com.danielsan.notafiscalcidada.extensions.showLoading
import br.com.danielsan.notafiscalcidada.extensions.subscribeOnIo
import br.com.danielsan.notafiscalcidada.shared.Presenter
import io.reactivex.Observable
import java.util.*

/**
 * Created by daniel on 01/10/17.
 */
class InvoicesPresenter(
        private val invoiceRepository: InvoiceRepository
) : Presenter<InvoicesContract>() {

    override fun attachView(view: InvoicesContract) {
        super.attachView(view)
        Calendar.getInstance().apply { onFilterSelected(get(Calendar.MONTH) + 1, get(Calendar.YEAR)) }
    }

    fun onClickInvoice(invoice: Invoice) {
    }

    fun onFilterSelected(semester: Int) {
        val year = 2000 + ((semester - 1) / 2)
        val rangeObservable = if (2 - (semester % 2) == 1) {
            Observable.range(1, 6)
        } else {
            Observable.range(6, 12)
        }
        rangeObservable
                .subscribeOnIo()
                .concatMap { onFilterInternal(it, year) }
                .toList()
                .observeOnMainUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    view?.clearInvoices()
                    view?.showInvoices(mutableListOf<Invoice>().apply {
                        it.forEach { addAll(it) }
                    })
                }, {
                    showErrorMessage(it)
                })
    }

    fun onFilterSelected(month: Int, year: Int) {
        onFilterInternal(month, year)
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    view?.clearInvoices()
                    view?.showInvoices(it)
                }, {
                    showErrorMessage(it)
                })
    }

    private fun onFilterInternal(month: Int, year: Int): Observable<List<Invoice>> {
        return invoiceRepository.invoices("%d%02d".format(year, month))
                .toObservable()
    }

}

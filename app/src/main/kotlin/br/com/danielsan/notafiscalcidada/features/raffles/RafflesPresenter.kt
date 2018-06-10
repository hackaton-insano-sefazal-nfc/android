package br.com.danielsan.notafiscalcidada.features.raffles

import br.com.danielsan.notafiscalcidada.domain.raffle.Raffle
import br.com.danielsan.notafiscalcidada.domain.raffle.RaffleRepository
import br.com.danielsan.notafiscalcidada.extensions.dismissLoading
import br.com.danielsan.notafiscalcidada.extensions.observeOnMainUi
import br.com.danielsan.notafiscalcidada.extensions.showErrorMessage
import br.com.danielsan.notafiscalcidada.extensions.showLoading
import br.com.danielsan.notafiscalcidada.extensions.subscribeOnIo
import br.com.danielsan.notafiscalcidada.shared.Presenter

/**
 * Created by daniel on 01/10/17.
 */
class RafflesPresenter(
    private val raffleRepository: RaffleRepository
) : Presenter<RafflesContract>() {

    override fun attachView(view: RafflesContract) {
        super.attachView(view)
        raffleRepository.raffles()
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    this.view?.showRaffles(it)
                }, {
                    showErrorMessage(it)
                })
    }

    fun onClickRaffle(raffle: Raffle) {
        view?.showRaffleOptions(raffle)
    }

    fun onYourTicketsSelected(raffle: Raffle) {
        raffleRepository.tickets(raffle.sequential)
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    if (it.isNotEmpty()) {
                        view?.showTickets(it)
                    } else {
                        view?.showWithoutTickets()
                    }
                }, {
                    showErrorMessage(it)
                })
    }

    fun onYourWinningTicketsSelected(raffle: Raffle) {
        raffleRepository.winningTickets(raffle.sequential)
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    if (it.isNotEmpty()) {
                        view?.showWinningTickets(it)
                    } else {
                        view?.showWithoutWinningTickets()
                    }
                }, {
                    showErrorMessage(it)
                })
    }

}

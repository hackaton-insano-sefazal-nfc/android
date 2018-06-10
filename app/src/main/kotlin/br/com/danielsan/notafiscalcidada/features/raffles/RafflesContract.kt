package br.com.danielsan.notafiscalcidada.features.raffles

import br.com.danielsan.notafiscalcidada.base.BaseView
import br.com.danielsan.notafiscalcidada.domain.raffle.Raffle
import br.com.danielsan.notafiscalcidada.domain.raffle.Ticket
import br.com.danielsan.notafiscalcidada.domain.raffle.WinningTicket

/**
 * Created by daniel on 01/10/17.
 */
interface RafflesContract : BaseView {

    fun showRaffles(raffleList: List<Raffle>)

    fun showRaffleOptions(raffle: Raffle)

    fun showTickets(ticketList: List<Ticket>)

    fun showWithoutTickets()

    fun showWinningTickets(winningTicketList: List<WinningTicket>)

    fun showWithoutWinningTickets()

}

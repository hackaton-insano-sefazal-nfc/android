package br.com.danielsan.notafiscalcidada.domain.raffle

import io.reactivex.Single
import java.util.*

/**
 * Created by daniel on 18/08/17.
 */
class FakeRaffleRepositoryImpl : RaffleRepository {

    override fun raffles(): Single<List<Raffle>> {
        val raffle = Raffle(
                code = 312,
                sequential = 123,
                description = "Dia dos Pais",
                dateOfRealization = Date()
        )
        return Single.just(mutableListOf(
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle,
                raffle
        ))
    }

    override fun tickets(ticketNumber: Int): Single<List<Ticket>> {
        val ticket = Ticket(ticketNumber = 342764)
        return Single.just(mutableListOf(
                ticket,
                ticket,
                ticket,
                ticket,
                ticket,
                ticket,
                ticket,
                ticket,
                ticket,
                ticket
        ))
    }

    override fun winningTickets(ticketNumber: Int): Single<List<WinningTicket>> {
        val ticket = Ticket(ticketNumber = 342764)
        val winningTicket = WinningTicket(
                premiumAmount = 123,
                ticket = ticket
        )
        return Single.just(mutableListOf(
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket,
                winningTicket
        ))
    }

}
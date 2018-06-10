package br.com.danielsan.notafiscalcidada.domain.raffle

import br.com.danielsan.notafiscalcidada.extensions.socketTimeoutRetry
import io.reactivex.Single
import retrofit2.Retrofit

/**
 * Created by daniel on 18/08/17.
 */
class RaffleRepositoryImpl(private val retrofit: Retrofit) : RaffleRepository {

    private val api by lazy { retrofit.create(RaffleRepository::class.java) }

    override fun raffles(): Single<List<Raffle>> {
        return api.raffles()
                .socketTimeoutRetry()
    }

    override fun tickets(ticketNumber: Int): Single<List<Ticket>> {
        return api.tickets(ticketNumber)
                .socketTimeoutRetry()
    }

    override fun winningTickets(ticketNumber: Int): Single<List<WinningTicket>> {
        return api.winningTickets(ticketNumber)
                .socketTimeoutRetry()
    }

}

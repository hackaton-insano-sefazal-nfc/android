package br.com.danielsan.notafiscalcidada.domain.raffle

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by daniel on 18/08/17.
 */
interface RaffleRepository {

    @GET("sfz-nfcidada-api/api/public/sorteio")
    fun raffles(): Single<List<Raffle>>

    @GET("sfz-nfcidada-api/api/public/bilhete/{number}")
    fun tickets(@Path("number") ticketNumber: Int): Single<List<Ticket>>

    @GET("sfz-nfcidada-api/api/public/bilheteContemplado/{number}")
    fun winningTickets(@Path("number") ticketNumber: Int): Single<List<WinningTicket>>

}
package br.com.danielsan.notafiscalcidada.domain.user

import br.com.danielsan.notafiscalcidada.domain.raffle.Raffle
import io.reactivex.Single
import java.util.*

/**
 * Created by daniel on 19/08/17.
 */
class FakeUserRepositoryImpl: UserRepository {

    override fun mainInfo(): Single<UserResponse> {
        val raffle = Raffle(
                description = "Dias dos Pais",
                dateOfRealization = Date()
        )
        return Single.just(UserResponse(
                credits = 7.51f,
                invoices = 2,
                nextRaffle = raffle,
                numberOfTickets = 2
        ))
    }

    override fun credits(): Single<CreditsResponse> {
        return Single.just(CreditsResponse(42.00f))
    }

}

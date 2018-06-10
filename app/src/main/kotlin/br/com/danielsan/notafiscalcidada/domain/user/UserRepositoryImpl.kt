package br.com.danielsan.notafiscalcidada.domain.user

import br.com.danielsan.notafiscalcidada.Constants
import br.com.danielsan.notafiscalcidada.domain.invoice.InvoiceRepository
import br.com.danielsan.notafiscalcidada.domain.raffle.RaffleRepository
import br.com.danielsan.notafiscalcidada.extensions.socketTimeoutRetry
import io.reactivex.Single
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by daniel on 19/08/17.
 */
class UserRepositoryImpl(
    private val retrofit: Retrofit,
    private val raffleRepository: RaffleRepository,
    private val invoiceRepository: InvoiceRepository
): UserRepository {

    private val api by lazy { retrofit.create(UserRepository::class.java) }

    override fun mainInfo(): Single<UserResponse> {
        val userResponse = UserResponse()
        return credits()
                .doOnSuccess { userResponse.credits = it.value }
                .flatMap { raffleRepository.raffles() }
                .doOnSuccess { raffleList ->
                    raffleList
                            ?.takeIf { it.isNotEmpty() }
                            ?.last()
                            ?.takeIf { it.dateOfRealization?.after(Date()) ?: false }
                            ?.let { userResponse.nextRaffle = it }
                }
                .flatMap {
                    if (userResponse.nextRaffle != null) {
                        raffleRepository.tickets(userResponse.nextRaffle!!.sequential)
                    } else {
                        Single.just(emptyList())
                    }
                }
                .doOnSuccess { userResponse.numberOfTickets = it.size }
                .flatMap {
                    if (userResponse.nextRaffle != null) {
                        val dateFormat = SimpleDateFormat("yyyyMM", Constants.LOCALE)
                        val date = dateFormat.format(userResponse.nextRaffle!!.dateOfRealization!!)
                        invoiceRepository.invoices(date)
                    } else {
                        Single.just(emptyList())
                    }
                }
                .doOnSuccess { userResponse.invoices = it.size }
                .map { userResponse }
                .socketTimeoutRetry()
    }

    override fun credits(): Single<CreditsResponse> {
        return api.credits()
    }

}

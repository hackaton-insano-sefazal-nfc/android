package br.com.danielsan.notafiscalcidada.features.main

import br.com.danielsan.notafiscalcidada.base.BaseView
import br.com.danielsan.notafiscalcidada.domain.raffle.Raffle
import br.com.danielsan.notafiscalcidada.domain.user.UserResponse

/**
 * Created by daniel on 19/08/17.
 */
interface MainContract : BaseView {

    fun navigateToAuthenticate()

    fun navigateToDenounce()

    fun showUserInfo(userResponse: UserResponse)

    fun navigateToYourTicket()

    fun navigateToRanking()

    fun navigateToDonation()

    fun navigateToPush(cpf: String)

    fun navigateToAbout()

    fun navigateToOpenSource()

    fun navigateToInvoices()

    fun navigateToRaffles()

    fun showNextRaffle(raffle: Raffle)

}

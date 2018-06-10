package br.com.danielsan.notafiscalcidada.domain.user

import br.com.danielsan.notafiscalcidada.domain.raffle.Raffle
import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 19/08/17.
 */
data class UserResponse(
        var credits: Float = 0f,
        var invoices: Int = 0,
        var nextRaffle: Raffle? = null,
        @SerializedName("number_of_tickets") var numberOfTickets: Int = 0
)

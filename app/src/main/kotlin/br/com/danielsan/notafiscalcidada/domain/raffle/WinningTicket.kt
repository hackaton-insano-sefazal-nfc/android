package br.com.danielsan.notafiscalcidada.domain.raffle

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 01/10/17.
 */
data class WinningTicket(
        @SerializedName("valorPremio") var premiumAmount: Int = 0,
        @SerializedName("bilhete") var ticket: Ticket? = null
)
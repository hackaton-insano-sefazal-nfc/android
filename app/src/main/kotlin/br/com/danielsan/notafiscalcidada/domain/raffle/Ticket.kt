package br.com.danielsan.notafiscalcidada.domain.raffle

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 01/10/17.
 */
data class Ticket(
        @SerializedName("numeroBilhete") var ticketNumber: Int = 0,
        @SerializedName("sorteio") var raffle: Raffle? = null,
        @SerializedName("numeroDocumento") var documentNumber: String = ""
)

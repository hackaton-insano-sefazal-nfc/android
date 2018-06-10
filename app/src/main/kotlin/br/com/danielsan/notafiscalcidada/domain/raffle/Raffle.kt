package br.com.danielsan.notafiscalcidada.domain.raffle

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by daniel on 19/08/17.
 */
data class Raffle(
        @SerializedName("sequencial") var sequential: Int = 0,
        @SerializedName("dataRealizacao") var dateOfRealization: Date? = null,
        @SerializedName("codigoSorteio") var code: Int = 0,
        @SerializedName("descricao") var description: String = ""
)

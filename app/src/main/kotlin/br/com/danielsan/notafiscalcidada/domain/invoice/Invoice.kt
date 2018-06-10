package br.com.danielsan.notafiscalcidada.domain.invoice

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by daniel on 02/10/17.
 */
data class Invoice(
        @SerializedName("dataEmissao") var issueDate: Date? = null,
        @SerializedName("numeroNotaFiscal") var number: String = "",
        @SerializedName("tipoNotaFiscal") var type: String = "",
        @SerializedName("valorTotal") var totalValue: Float = 0f,
        @SerializedName("descricaoContribuinte") var taxpayer: String = "",
        @SerializedName("descricaoMotivoAnulacao") var cancellationReason: String = "",
        @SerializedName("numeroEmitente") var issuerNumber: String = "",
        @SerializedName("valorCredito") var creditValue: Float = 0f
)

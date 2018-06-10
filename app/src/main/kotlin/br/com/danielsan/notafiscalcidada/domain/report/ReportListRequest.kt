package br.com.danielsan.notafiscalcidada.domain.report

import com.google.gson.annotations.SerializedName

/**
 * Created by saturo on 20/08/17.
 */
data class ReportListRequest(
        var cnpj : String,
        @SerializedName("dataFim") var endDate : String,
        @SerializedName("dataInicio") var startDate : String,
        @SerializedName("tipoDenuncia") var reportType : String
)
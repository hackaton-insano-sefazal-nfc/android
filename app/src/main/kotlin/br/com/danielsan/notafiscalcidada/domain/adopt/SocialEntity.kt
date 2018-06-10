package br.com.danielsan.notafiscalcidada.domain.adopt

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 01/10/17.
 */
data class SocialEntity(
        var id: Int = 0,
        var cnpj: String = "",
        @SerializedName("razaoSocial") var socialName: String = ""
)

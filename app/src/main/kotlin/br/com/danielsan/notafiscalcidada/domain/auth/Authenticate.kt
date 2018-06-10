package br.com.danielsan.notafiscalcidada.domain.auth

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 18/08/17.
 */
data class Authenticate(
        var login: String = "",
        @SerializedName("idAutorizacao") var authorizationId: Int = 0,
        @SerializedName("tokenApp") var sefazToken: String = ""
)
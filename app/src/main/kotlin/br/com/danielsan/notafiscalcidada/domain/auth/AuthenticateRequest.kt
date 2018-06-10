package br.com.danielsan.notafiscalcidada.domain.auth

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 18/08/17.
 */
data class AuthenticateRequest(
        var login: String,
        @SerializedName("nomeDispositivo") var deviceName: String,
        @SerializedName("tokenApp") var sefazToken: String
)

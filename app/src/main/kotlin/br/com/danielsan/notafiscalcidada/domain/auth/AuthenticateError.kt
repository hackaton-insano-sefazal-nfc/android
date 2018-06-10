package br.com.danielsan.notafiscalcidada.domain.auth

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 18/08/17.
 */
data class AuthenticateError(
        @SerializedName("codigo") var code: Int = 0,
        @SerializedName("mensagem") var message: String = ""
)

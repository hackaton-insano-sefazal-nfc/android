package br.com.danielsan.notafiscalcidada.domain.auth

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 18/08/17.
 */
class AuthenticateRequestResponse(
        @SerializedName("idAutorizacao") var authorizationId: Int = 0,
        @SerializedName("urlAutorizacao") var authorizationUrl: String? = null
)

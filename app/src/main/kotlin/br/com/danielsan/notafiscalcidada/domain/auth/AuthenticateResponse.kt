package br.com.danielsan.notafiscalcidada.domain.auth

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 18/08/17.
 */
data class AuthenticateResponse(
        @SerializedName("id_token") var tokenId: String = ""
)

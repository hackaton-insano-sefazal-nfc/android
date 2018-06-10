package br.com.danielsan.notafiscalcidada.domain.user

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 08/10/17.
 */
data class CreditsResponse(@SerializedName("valorCredito") var value: Float = 0f)

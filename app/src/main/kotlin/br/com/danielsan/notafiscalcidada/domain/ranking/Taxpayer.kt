package br.com.danielsan.notafiscalcidada.domain.ranking

import com.google.gson.annotations.SerializedName

/**
 * Created by daniel on 05/10/17.
 */
data class Taxpayer(
        var points: Int = 0,
        var position: Int = 0,
        @SerializedName("current_user") var isCurrentUser: Boolean = false
)

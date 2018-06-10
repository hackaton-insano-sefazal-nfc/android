package br.com.danielsan.notafiscalcidada.domain

import br.com.danielsan.notafiscalcidada.data.persistence.Preferences
import br.com.danielsan.notafiscalcidada.data.persistence.PreferencesKey
import br.com.danielsan.notafiscalcidada.data.persistence.getString
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by saturo on 18/08/17.
 */
class TokenInterceptor(private val preferences: Preferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences.getString(PreferencesKey.TOKEN_ID)
        return chain.proceed(chain
                .request()
                .newBuilder()
                .header("Authorization", token)
                .build()
        )
    }

}

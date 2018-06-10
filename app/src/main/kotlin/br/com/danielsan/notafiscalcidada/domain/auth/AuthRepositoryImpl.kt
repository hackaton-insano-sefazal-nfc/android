package br.com.danielsan.notafiscalcidada.domain.auth

import br.com.danielsan.notafiscalcidada.extensions.socketTimeoutRetry
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created by daniel on 18/08/17.
 */
class AuthRepositoryImpl(
        private val retrofit: Retrofit,
        private val sefazToken: String
) : AuthRepository {

    private var converter: Converter<ResponseBody, AuthenticateError>? = null
    private val api by lazy {
        converter = retrofit.responseBodyConverter(AuthenticateError::class.java, arrayOf<Annotation>())
        retrofit.create(AuthRepository::class.java)
    }

    override fun authenticate(login: String, authorizationId: Int): Single<AuthenticateResponse> {
        return api.authenticate(Authenticate(login, authorizationId, sefazToken))
                .socketTimeoutRetry()
    }

    override fun authenticate(body: Authenticate): Single<AuthenticateResponse> {
        throw UnsupportedOperationException()
    }

    override fun authenticateRequest(login: String, deviceName: String): Single<AuthenticateRequestResponse> {
        return api.authenticateRequest(AuthenticateRequest(login, deviceName, sefazToken))
                .socketTimeoutRetry()
    }

    override fun authenticateRequest(body: AuthenticateRequest): Single<AuthenticateRequestResponse> {
        throw UnsupportedOperationException()
    }

    override fun parseError(responseBody: ResponseBody?): AuthenticateError? {
        return converter?.convert(responseBody)
    }

}

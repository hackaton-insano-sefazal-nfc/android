package br.com.danielsan.notafiscalcidada.domain.auth

import io.reactivex.Single
import okhttp3.ResponseBody

/**
 * Created by daniel on 24/09/17.
 */
class FakeAuthRepositoryImpl : AuthRepository {

    override fun authenticate(login: String, authorizationId: Int): Single<AuthenticateResponse> {
        return Single.just(AuthenticateResponse())
    }

    override fun authenticate(body: Authenticate): Single<AuthenticateResponse> {
        throw UnsupportedOperationException()
    }

    override fun authenticateRequest(login: String, deviceName: String): Single<AuthenticateRequestResponse> {
        return Single.just(AuthenticateRequestResponse(authorizationId = 1))
    }

    override fun authenticateRequest(body: AuthenticateRequest): Single<AuthenticateRequestResponse> {
        throw UnsupportedOperationException()
    }

    override fun parseError(responseBody: ResponseBody?): AuthenticateError? {
        return null
    }

}

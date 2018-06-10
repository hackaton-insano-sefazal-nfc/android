package br.com.danielsan.notafiscalcidada.domain.auth

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by daniel on 18/08/17.
 */
interface AuthRepository {

    // TODO: Search for a better way to do this
    fun authenticate(login: String, authorizationId: Int): Single<AuthenticateResponse>

    @POST("api/public/autenticar")
    fun authenticate(@Body body: Authenticate): Single<AuthenticateResponse>

    // TODO: Search for a better way to do this
    fun authenticateRequest(login: String, deviceName: String): Single<AuthenticateRequestResponse>

    @POST("sfz-habilitacao-aplicativo-api/api/public/autorizacao-aplicativo/solicitar")
    fun authenticateRequest(@Body body: AuthenticateRequest): Single<AuthenticateRequestResponse>

    fun parseError(responseBody: ResponseBody?): AuthenticateError?

}

package br.com.danielsan.notafiscalcidada.domain.user

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by daniel on 19/08/17.
 */
interface UserRepository {

    fun mainInfo(): Single<UserResponse>

    @GET("sfz-nfcidada-api/api/public/consultarCredito")
    fun credits(): Single<CreditsResponse>

}

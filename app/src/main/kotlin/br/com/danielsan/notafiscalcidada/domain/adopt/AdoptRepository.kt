package br.com.danielsan.notafiscalcidada.domain.adopt

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by daniel on 01/10/17.
 */
interface AdoptRepository {

    @GET("sfz-nfcidada-api/api/public/entidadeSocial")
    fun socialEntities(): Single<List<SocialEntity>>

    @GET("sfz-nfcidada-api/api/public/entidadeSocial/{id}/adotar")
    fun adopt(@Path("id") id: Int): Single<String>

}

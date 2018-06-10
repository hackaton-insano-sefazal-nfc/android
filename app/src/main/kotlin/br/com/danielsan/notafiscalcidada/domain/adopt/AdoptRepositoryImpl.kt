package br.com.danielsan.notafiscalcidada.domain.adopt

import br.com.danielsan.notafiscalcidada.extensions.socketTimeoutRetry
import io.reactivex.Single
import retrofit2.Retrofit

/**
 * Created by daniel on 01/10/17.
 */
class AdoptRepositoryImpl(private val retrofit: Retrofit) : AdoptRepository {

    private val api by lazy { retrofit.create(AdoptRepository::class.java) }

    override fun socialEntities(): Single<List<SocialEntity>> {
        return api.socialEntities()
                .socketTimeoutRetry()
    }

    override fun adopt(id: Int): Single<String> {
        return api.adopt(id)
                .socketTimeoutRetry()
    }

}
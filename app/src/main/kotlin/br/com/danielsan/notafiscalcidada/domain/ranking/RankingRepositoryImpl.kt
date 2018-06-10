package br.com.danielsan.notafiscalcidada.domain.ranking

import br.com.danielsan.notafiscalcidada.extensions.socketTimeoutRetry
import io.reactivex.Single
import retrofit2.Retrofit

/**
 * Created by daniel on 05/10/17.
 */
class RankingRepositoryImpl(private val retrofit: Retrofit) : RankingRepository {

    private val api by lazy { retrofit.create(RankingRepository::class.java) }

    override fun ranking(): Single<RankingResponse> {
        return api.ranking()
                .socketTimeoutRetry()
    }

}

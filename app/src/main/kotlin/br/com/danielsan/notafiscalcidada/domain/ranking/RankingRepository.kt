package br.com.danielsan.notafiscalcidada.domain.ranking

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by daniel on 05/10/17.
 */
interface RankingRepository {

    // FIXME: This if for tests Only
    @GET("facilitate/ranking/")
    fun ranking(): Single<RankingResponse>

}

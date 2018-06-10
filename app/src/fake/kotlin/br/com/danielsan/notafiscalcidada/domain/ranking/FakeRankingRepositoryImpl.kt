package br.com.danielsan.notafiscalcidada.domain.ranking

import io.reactivex.Single

/**
 * Created by daniel on 05/10/17.
 */
class FakeRankingRepositoryImpl : RankingRepository {

    override fun ranking(): Single<RankingResponse> {
        val taxpayer1 = Taxpayer(
                points = 10,
                position = 1,
                isCurrentUser = false
        )
        val taxpayer2 = Taxpayer(
                points = 9,
                position = 2,
                isCurrentUser = false
        )
        val taxpayer3 = Taxpayer(
                points = 8,
                position = 3,
                isCurrentUser = false
        )
        val taxpayer4 = Taxpayer(
                points = 7,
                position = 4,
                isCurrentUser = false
        )
        val taxpayer = Taxpayer(
                points = 1,
                position = 50,
                isCurrentUser = true
        )
        return Single.just(RankingResponse(mutableListOf(taxpayer1, taxpayer2, taxpayer3, taxpayer4, taxpayer)))
    }

}

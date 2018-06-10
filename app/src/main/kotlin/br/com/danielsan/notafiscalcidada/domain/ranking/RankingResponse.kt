package br.com.danielsan.notafiscalcidada.domain.ranking

/**
 * Created by daniel on 05/10/17.
 */
data class RankingResponse(
        var ranking: List<Taxpayer> = emptyList()
)

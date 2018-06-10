package br.com.danielsan.notafiscalcidada.domain.adopt

import io.reactivex.Single

/**
 * Created by daniel on 01/10/17.
 */
class FakeAdoptRepositoryImpl : AdoptRepository {

    override fun socialEntities(): Single<List<SocialEntity>> {
        val socialEntity = SocialEntity(
                id = 12,
                cnpj = "38749382988912",
                socialName = "ASSOCIACAO PESTALOZZI DE MACEIO"
        )
        return Single.just(mutableListOf(
                socialEntity,
                socialEntity,
                socialEntity,
                socialEntity,
                socialEntity,
                socialEntity,
                socialEntity,
                socialEntity,
                socialEntity,
                socialEntity
        ))
    }

    override fun adopt(id: Int): Single<String> {
        return Single.just("Operação realizada com Sucesso!")
    }

}

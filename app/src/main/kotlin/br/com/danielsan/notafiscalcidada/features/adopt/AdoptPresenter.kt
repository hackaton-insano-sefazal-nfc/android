package br.com.danielsan.notafiscalcidada.features.adopt

import br.com.danielsan.notafiscalcidada.domain.adopt.AdoptRepository
import br.com.danielsan.notafiscalcidada.domain.adopt.SocialEntity
import br.com.danielsan.notafiscalcidada.extensions.*
import br.com.danielsan.notafiscalcidada.shared.Presenter

/**
 * Created by daniel on 01/10/17.
 */
class AdoptPresenter(
    private val adoptRepository: AdoptRepository
) : Presenter<AdoptContract>() {

    override fun attachView(view: AdoptContract) {
        super.attachView(view)
        adoptRepository.socialEntities()
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    this.view?.showSocialEntities(it)
                }, {
                    showErrorMessage(it)
                })
    }

    fun onClickSocialEntity(socialEntity: SocialEntity) {
        view?.showAdoptConfirmation(socialEntity)
    }

    fun onClickAdoptConfirmed(socialEntity: SocialEntity) {
        adoptRepository.adopt(socialEntity.id)
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    this.view?.navigateToAdoptFinished(socialEntity)
                }, {
                    showErrorMessage(it)
                })
    }

}

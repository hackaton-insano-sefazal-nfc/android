package br.com.danielsan.notafiscalcidada.features.main

import br.com.danielsan.notafiscalcidada.data.persistence.Preferences
import br.com.danielsan.notafiscalcidada.data.persistence.PreferencesKey
import br.com.danielsan.notafiscalcidada.data.persistence.contains
import br.com.danielsan.notafiscalcidada.data.persistence.getString
import br.com.danielsan.notafiscalcidada.data.persistence.remove
import br.com.danielsan.notafiscalcidada.domain.user.UserRepository
import br.com.danielsan.notafiscalcidada.domain.user.UserResponse
import br.com.danielsan.notafiscalcidada.extensions.dismissLoading
import br.com.danielsan.notafiscalcidada.extensions.observeOnMainUi
import br.com.danielsan.notafiscalcidada.extensions.showErrorMessage
import br.com.danielsan.notafiscalcidada.extensions.showLoading
import br.com.danielsan.notafiscalcidada.extensions.subscribeOnIo
import br.com.danielsan.notafiscalcidada.shared.Presenter

/**
 * Created by daniel on 19/08/17.
 */
class MainPresenter(
    private val userRepository: UserRepository,
    private val preferences: Preferences
) : Presenter<MainContract>() {

    private var userResponse: UserResponse? = null

    override fun attachView(view: MainContract) {
        super.attachView(view)
        if (!preferences.contains(PreferencesKey.TOKEN_ID)) {
            this.view?.navigateToAuthenticate()
        } else {
            userRepository.mainInfo()
                    .subscribeOnIo()
                    .observeOnMainUi()
                    .doOnSubscribe{ showLoading() }
                    .doAfterTerminate { dismissLoading() }
                    .subscribe({
                        userResponse = it
                        if (it != null) {
                            this.view?.showUserInfo(it)
                        }
                    }, {
                        showErrorMessage(it)
                    })
        }
    }

    fun onClickDenounce() {
        view?.navigateToDenounce()
    }

    fun onClickYourTicket() {
        view?.navigateToYourTicket()
    }

    fun onClickRanking() {
        view?.navigateToRanking()
    }

    fun onClickDonation() {
        view?.navigateToDonation()
    }

    fun onClickPush() {
        view?.navigateToPush(preferences.getString(PreferencesKey.USER_CPF))
    }

    fun onClickAbout() {
        view?.navigateToAbout()
    }

    fun onClickOpenSource() {
        view?.navigateToOpenSource()
    }

    fun onClickInvoices() {
        view?.navigateToInvoices()
    }

    fun onClickRaffles() {
        view?.navigateToRaffles()
    }

    fun onClickNextRaffle() {
        userResponse?.nextRaffle?.let { view?.showNextRaffle(it) }
    }

    fun onClickLogout() {
        preferences.remove(PreferencesKey.USER_CPF)
        preferences.remove(PreferencesKey.TOKEN_ID)
        view?.navigateToAuthenticate()
    }

}

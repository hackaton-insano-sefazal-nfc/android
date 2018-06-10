package br.com.danielsan.notafiscalcidada.features.authentication

import br.com.danielsan.notafiscalcidada.data.persistence.Preferences
import br.com.danielsan.notafiscalcidada.data.persistence.PreferencesKey
import br.com.danielsan.notafiscalcidada.data.persistence.put
import br.com.danielsan.notafiscalcidada.domain.auth.AuthRepository
import br.com.danielsan.notafiscalcidada.domain.auth.AuthenticateError
import br.com.danielsan.notafiscalcidada.domain.auth.AuthenticateRequestResponse
import br.com.danielsan.notafiscalcidada.extensions.dismissLoading
import br.com.danielsan.notafiscalcidada.extensions.observeOnMainUi
import br.com.danielsan.notafiscalcidada.extensions.showErrorMessage
import br.com.danielsan.notafiscalcidada.extensions.showLoading
import br.com.danielsan.notafiscalcidada.extensions.subscribeOnIo
import br.com.danielsan.notafiscalcidada.shared.Presenter
import retrofit2.HttpException

/**
 * Created by daniel on 18/08/17.
 */
class AuthPresenter(
    private var authRepository: AuthRepository,
    private val preferences: Preferences,
    private val deviceName: String
) : Presenter<AuthContract>() {

    private var authenticateRequestResponse: AuthenticateRequestResponse? = null

    fun onClickAuthenticate(cpf: String) {
        if (view?.validate() == false) return

        val login = cpf.replace(Regex("[\\D]"), "")
        authRepository.authenticateRequest(login, deviceName)
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe{ showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    authenticateRequestResponse = it
                    view?.navigateToUpdate(it.authorizationUrl ?: "http://dec.sefaz.al.gov.br/habilitacao/#")
                }, {
                    showErrorMessage(it)
                })
    }

    fun onClickUpdate(cpf: String) {
        if (authenticateRequestResponse == null) return

        val login = cpf.replace(Regex("[\\D]"), "")
        authRepository.authenticate(login, authenticateRequestResponse!!.authorizationId)
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe{ showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    preferences.put(PreferencesKey.USER_CPF, login)
                    preferences.put(PreferencesKey.TOKEN_ID, "Bearer ${it.tokenId}")
                    view?.navigateToMain()
                }, {
                    if (it is HttpException) {
                        handleError(authRepository.parseError(it.response().errorBody()))
                    } else {
                        showErrorMessage(it)
                    }
                })
    }

    fun onClickNoAccountOrDoubt() {
        view?.navigateToNoAccountOrDoubt()
    }

    private fun handleError(authenticateError: AuthenticateError?) {
        if (authenticateError != null) {
            view?.showMessage(authenticateError.message)
        } else {
            view?.showDefaultUpdateAuthStatus()
        }
    }

}

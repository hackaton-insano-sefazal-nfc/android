package br.com.danielsan.notafiscalcidada.features.authentication

import br.com.danielsan.notafiscalcidada.base.BaseView

/**
 * Created by daniel on 18/08/17.
 */
interface AuthContract : BaseView {

    fun validate(): Boolean

    fun navigateToNoAccountOrDoubt()

    fun navigateToUpdate(authorizationUrl: String)

    fun navigateToMain()

    fun showDefaultUpdateAuthStatus()

}

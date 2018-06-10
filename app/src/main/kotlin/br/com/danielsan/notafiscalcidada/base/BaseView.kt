package br.com.danielsan.notafiscalcidada.base

/**
 * Created by daniel on 18/08/17.
 */
interface BaseView {

    fun showMessage(message: CharSequence)

    fun showLoading()

    fun dismissLoading()

}

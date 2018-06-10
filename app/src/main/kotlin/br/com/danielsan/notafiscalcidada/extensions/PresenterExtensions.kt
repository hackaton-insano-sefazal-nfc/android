package br.com.danielsan.notafiscalcidada.extensions

import br.com.danielsan.notafiscalcidada.base.BaseView
import br.com.danielsan.notafiscalcidada.shared.Presenter
import timber.log.Timber
import java.net.SocketTimeoutException

/**
 * Created by daniel on 18/08/17.
 */

inline fun <V : BaseView> Presenter<V>.showErrorMessage(throwable: Throwable) {
    Timber.e(throwable)
    view?.showMessage(getFriendlyMessage(throwable))
}

fun <V : BaseView> Presenter<V>.showLoading() {
    view?.showLoading()
}

fun <V : BaseView> Presenter<V>.dismissLoading() {
    view?.dismissLoading()
}

fun getFriendlyMessage(throwable: Throwable): String = when (throwable) {
    is SocketTimeoutException -> "Servidor da Sefaz não respondeu"
    else -> "Error inesperado, tente novamente"
}

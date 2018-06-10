package br.com.danielsan.notafiscalcidada.shared

import android.support.annotation.CallSuper

import java.lang.ref.WeakReference

/**
 * Created by daniel on 04/09/16.
 */
abstract class Presenter<View> {

    val isAttached: Boolean
        get() = viewReference?.get() != null
    val view: View?
        get() = viewReference?.get()
    private var viewReference: WeakReference<View>? = null

    @CallSuper open fun attachView(view: View) {
        viewReference?.clear()
        viewReference = WeakReference(view)
    }

    open fun start() { }

    open fun stop() { }

    @CallSuper open fun detachView() {
        viewReference?.clear()
        viewReference = null
    }

}

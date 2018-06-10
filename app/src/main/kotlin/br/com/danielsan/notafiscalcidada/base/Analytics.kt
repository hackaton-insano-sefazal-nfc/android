package br.com.danielsan.notafiscalcidada.base

/**
 * Created by daniel on 09/10/17.
 */
interface Analytics {

    fun logEvent(event: String, params: Map<String, Any>)

}

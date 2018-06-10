package br.com.danielsan.notafiscalcidada.features.push

import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.data.persistence.PreferencesKey
import br.com.danielsan.notafiscalcidada.data.persistence.SharedPreferences
import br.com.danielsan.notafiscalcidada.data.persistence.contains
import br.com.danielsan.notafiscalcidada.data.persistence.getString
import io.rapidpro.sdk.FcmClient
import io.rapidpro.sdk.core.models.Group
import io.rapidpro.sdk.core.models.v2.Contact
import io.rapidpro.sdk.services.FcmClientRegistrationIntentService
import timber.log.Timber
import java.util.*

/**
 * Created by daniel on 20/08/17.
 */
class PushRegistrationService : FcmClientRegistrationIntentService() {

    override fun onFcmRegistered(pushIdentity: String?, contact: Contact?) {
        super.onFcmRegistered(pushIdentity, contact)
        SharedPreferences(this@PushRegistrationService)
                .takeIf { it.contains(PreferencesKey.USER_CPF) }
                ?.let { FcmClient.registerContact(it.getString(PreferencesKey.USER_CPF)) }
        try {
            contact?.apply {
                groups = mutableListOf(Group().apply { uuid = getString(R.string.push_general_group) })
                fields = hashMapOf<String, Any>("RegistrationDate" to Date())
            }
            FcmClient.getServices().saveContactV2(contact).execute()
        } catch (exception: Exception) {
            Timber.e(exception)
        }
    }

}

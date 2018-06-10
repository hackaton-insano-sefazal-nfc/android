package br.com.danielsan.notafiscalcidada.features.push

import android.support.v4.app.NotificationCompat
import io.rapidpro.sdk.services.FcmClientIntentService

/**
 * Created by daniel on 20/08/17.
 */
class ReceiverIntentService : FcmClientIntentService() {

    override fun onCreateLocalNotification(builder: NotificationCompat.Builder) {
        builder.setSmallIcon(io.rapidpro.sdk.R.drawable.fcm_client_ic_send_message)
        super.onCreateLocalNotification(builder)
    }

}

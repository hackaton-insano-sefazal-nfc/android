package br.com.danielsan.notafiscalcidada

import android.text.TextUtils
import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

/**
 * Created by daniel on 09/10/17.
 */
class CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR) {
            if (t != null) {
                Crashlytics.logException(t)
            } else if (!TextUtils.isEmpty(tag) && message.isNotBlank()) {
                Crashlytics.log(priority, tag, message)
            } else if (message.isNotBlank()) {
                Crashlytics.log(message)
            }
        }
    }

}

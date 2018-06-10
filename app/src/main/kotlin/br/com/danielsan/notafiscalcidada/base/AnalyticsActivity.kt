package br.com.danielsan.notafiscalcidada.base

import android.os.Bundle
import br.com.danielsan.notafiscalcidada.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Created by daniel on 09/10/17.
 */
abstract class AnalyticsActivity : BaseActivity(), Analytics {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAnalytics.setAnalyticsCollectionEnabled(!BuildConfig.DEBUG)
    }

    override fun logEvent(event: String, params: Map<String, Any>) {
        firebaseAnalytics.logEvent(event, Bundle().apply {
            params.forEach { (key, value) ->
                when (value) {
                    is String -> putString(key, value)
                    is CharSequence -> putCharSequence(key, value)
                    is Boolean -> putBoolean(key, value)
                    is Char -> putChar(key, value)
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is Float -> putFloat(key, value)
                    is Double -> putDouble(key, value)
                    is Short -> putShort(key, value)
                }
            }
        })
    }

}

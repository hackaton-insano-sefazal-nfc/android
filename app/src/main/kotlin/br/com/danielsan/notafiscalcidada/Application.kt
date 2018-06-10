package br.com.danielsan.notafiscalcidada

import android.content.Context
import android.support.multidex.MultiDex
import android.support.v4.content.ContextCompat
import br.com.danielsan.notafiscalcidada.di.DaggerAppComponent
import br.com.danielsan.notafiscalcidada.features.push.PushRegistrationService
import com.crashlytics.android.Crashlytics
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.fabric.sdk.android.Fabric
import io.rapidpro.sdk.FcmClient
import io.rapidpro.sdk.UiConfiguration
import timber.log.Timber

/**
 * Created by daniel on 18/08/17.
 */
class Application : DaggerApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(if (BuildConfig.DEBUG) {
            Timber.DebugTree()
        } else {
            Fabric.with(this, Crashlytics())
            CrashlyticsTree()
        })
        FcmClient.initialize(FcmClient.Builder(this)
                .setHost(getString(R.string.push_host))
                .setToken(getString(R.string.push_token))
                .setChannel(getString(R.string.push_channel))
                .setRegistrationServiceClass(PushRegistrationService::class.java)
                .setUiConfiguration(UiConfiguration()
                        .setPermissionMessage(getString(R.string.message_ilha_push_floating_chats))
                        .setIconResource(R.mipmap.ic_launcher)
                        .setIconFloatingChat(R.mipmap.ic_launcher)
                        .setTitleColor(ContextCompat.getColor(this, R.color.white))
                        .setTitleString(getString(R.string.app_name))))
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }

}

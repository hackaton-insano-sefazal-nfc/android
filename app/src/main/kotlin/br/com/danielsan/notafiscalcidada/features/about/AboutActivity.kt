package br.com.danielsan.notafiscalcidada.features.about

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityAboutBinding
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Created by lucas on 22/09/17.
 */
class AboutActivity : AnalyticsActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, AboutActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityAboutBinding>(this, R.layout.activity_about).apply {
            activity = this@AboutActivity
            toolbar?.apply {
                title.text = getString(R.string.about_title)
                back.setOnClickListener { finish() }
            }

            appOnGithub.setOnClickListener {
                logEvent(FirebaseAnalytics.Event.VIEW_ITEM, mapOf(FirebaseAnalytics.Param.ITEM_ID to "app_on_github"))
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hackaton-insano-sefazal-nfc")))
            }
        }
    }

    fun onClickAuthor(link: String) {
        logEvent(FirebaseAnalytics.Event.VIEW_ITEM, mapOf(FirebaseAnalytics.Param.ITEM_ID to link))
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

}

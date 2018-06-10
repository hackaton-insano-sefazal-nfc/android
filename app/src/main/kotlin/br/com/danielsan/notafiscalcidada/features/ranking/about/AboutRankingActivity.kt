package br.com.danielsan.notafiscalcidada.features.ranking.about

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityAboutRankingBinding
import br.com.danielsan.notafiscalcidada.features.main.MainActivity
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Created by lucas on 22/09/17.
 */
class AboutRankingActivity : AnalyticsActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, AboutRankingActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityAboutRankingBinding>(this, R.layout.activity_about_ranking).apply {
            toolbar?.apply {
                title.text = getString(R.string.about_ranking_title)
                back.setOnClickListener { finish() }
            }

            mainMenu.setOnClickListener {
                logEvent(FirebaseAnalytics.Event.VIEW_ITEM, mapOf(FirebaseAnalytics.Param.CONTENT to "about_ranking"))
                startActivity(MainActivity.createIntent(this@AboutRankingActivity))
                finish()
            }
        }
    }

}

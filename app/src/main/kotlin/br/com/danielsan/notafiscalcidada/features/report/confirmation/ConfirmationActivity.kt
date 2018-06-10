package br.com.danielsan.notafiscalcidada.features.report.confirmation

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityConfirmationBinding

/**
 * Created by saturo on 19/08/17.
 */
class ConfirmationActivity : AnalyticsActivity() {

    companion object {
        private const val EXTRA_TEXT = "text"
        private const val EXTRA_BUTTON_TITLE = "buttonTitle"

        fun createIntent(context: Context, text: String? = null, buttonTitle: String? = null)
                = Intent(context, ConfirmationActivity::class.java).apply {
            putExtra(EXTRA_TEXT, text)
            putExtra(EXTRA_BUTTON_TITLE, buttonTitle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityConfirmationBinding>(
                this,
                R.layout.activity_confirmation).run {
            text = intent?.getStringExtra(EXTRA_TEXT) ?: getString(R.string.confirmation_text)
            buttonTitle = intent?.getStringExtra(EXTRA_BUTTON_TITLE) ?: getString(R.string.ok)
            this.ok.setOnClickListener { finish() }
        }
    }

}

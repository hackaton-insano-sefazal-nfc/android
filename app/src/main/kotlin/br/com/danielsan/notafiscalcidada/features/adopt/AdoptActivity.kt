package br.com.danielsan.notafiscalcidada.features.adopt

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import br.com.danielsan.notafiscalcidada.Constants
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityListBinding
import br.com.danielsan.notafiscalcidada.domain.adopt.SocialEntity
import br.com.jansenfelipe.androidmask.Utils
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by daniel on 20/08/17.
 */
class AdoptActivity : AnalyticsActivity(), AdoptContract {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AdoptActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: AdoptPresenter
    private val adapter by lazy { SocialEntityAdapter(presenter) }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.toolbar?.apply {
            title.text = getText(R.string.title_share_your_note)
            back.setOnClickListener { finish() }
        }
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showSocialEntities(socialEntityList: List<SocialEntity>) {
        adapter.list.addAll(socialEntityList)
        adapter.notifyDataSetChanged()
    }

    override fun showAdoptConfirmation(socialEntity: SocialEntity) {
        logEvent("adopt_begin", mapOf(
                FirebaseAnalytics.Param.ITEM_NAME to socialEntity.socialName,
                FirebaseAnalytics.Param.ITEM_ID to socialEntity.id
        ))
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.confirmation))
                .setMessage(getString(R.string.message_adopt_confirmation, socialEntity.socialName, Utils.mask(Constants.CNPJ_MASK, socialEntity.cnpj)))
                .setPositiveButton(android.R.string.ok) { _, _ -> presenter.onClickAdoptConfirmed(socialEntity) }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    logEvent("adopt_cancel", mapOf(
                            FirebaseAnalytics.Param.ITEM_NAME to socialEntity.socialName,
                            FirebaseAnalytics.Param.ITEM_ID to socialEntity.id
                    ))
                }
                .show()
    }

    override fun navigateToAdoptFinished(socialEntity: SocialEntity) {
        logEvent("adopt_complete", mapOf(
                FirebaseAnalytics.Param.ITEM_NAME to socialEntity.socialName,
                FirebaseAnalytics.Param.ITEM_ID to socialEntity.id
        ))
        Toast.makeText(this, R.string.message_adopt_confirmed, Toast.LENGTH_LONG).show()
        finish()
    }

}

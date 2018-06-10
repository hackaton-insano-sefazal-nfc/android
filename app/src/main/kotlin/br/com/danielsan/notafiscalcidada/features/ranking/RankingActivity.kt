package br.com.danielsan.notafiscalcidada.features.ranking

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.base.AnalyticsActivity
import br.com.danielsan.notafiscalcidada.databinding.ActivityRankingBinding
import br.com.danielsan.notafiscalcidada.domain.ranking.RankingRepository
import br.com.danielsan.notafiscalcidada.extensions.observeOnMainUi
import br.com.danielsan.notafiscalcidada.extensions.subscribeOnIo
import br.com.danielsan.notafiscalcidada.features.ranking.about.AboutRankingActivity
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by daniel on 20/08/17.
 */
class RankingActivity : AnalyticsActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, RankingActivity::class.java)
        }
    }

    @Inject
    lateinit var rankingRepository: RankingRepository
    private val adapter by lazy { RakingAdapter() }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityRankingBinding>(this, R.layout.activity_ranking)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.toolbar?.apply {
            title.text = getString(R.string.activity_ranking_title)
            action.apply {
                visibility = View.VISIBLE
                setImageResource(R.drawable.ic_action_information)
                setOnClickListener { navigateToAboutRanking() }
            }
            back.setOnClickListener { finish() }
            binding.list.adapter = adapter
        }
        rankingRepository.ranking()
                .subscribeOnIo()
                .observeOnMainUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { dismissLoading() }
                .subscribe({
                    adapter.list.addAll(it.ranking)
                    adapter.notifyDataSetChanged()
                }, {
                    Timber.e(it)
                    showMessage(it.toString())
                })
    }

    private fun navigateToAboutRanking() {
        startActivity(AboutRankingActivity.createIntent(this))
    }

}

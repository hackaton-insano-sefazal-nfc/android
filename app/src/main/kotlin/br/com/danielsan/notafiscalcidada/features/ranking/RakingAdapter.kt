package br.com.danielsan.notafiscalcidada.features.ranking

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.danielsan.notafiscalcidada.databinding.ItemRankingBinding
import br.com.danielsan.notafiscalcidada.domain.ranking.Taxpayer
import br.com.danielsan.notafiscalcidada.shared.recyclerview.BasicListAdapter

/**
 * Created by daniel on 05/10/17.
 */
class RakingAdapter : BasicListAdapter<Taxpayer, TaxpayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaxpayerViewHolder {
        return TaxpayerViewHolder(ItemRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

}

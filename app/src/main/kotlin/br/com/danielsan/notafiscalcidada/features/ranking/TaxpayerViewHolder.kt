package br.com.danielsan.notafiscalcidada.features.ranking

import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.databinding.ItemRankingBinding
import br.com.danielsan.notafiscalcidada.domain.ranking.Taxpayer
import br.com.danielsan.notafiscalcidada.shared.recyclerview.BasicViewHolder

/**
 * Created by daniel on 05/10/17.
 */
class TaxpayerViewHolder(private val binding: ItemRankingBinding) : BasicViewHolder<Taxpayer>(binding.root) {

    override fun bind(obj: Taxpayer) {
        binding.points.text = "${obj.points}"
        binding.taxpayer.text = binding.root.context.getString(
            if (obj.isCurrentUser) {
                R.string.you_placed
            } else {
                R.string.placed
            }
        , obj.position)
        binding.executePendingBindings()
    }

}

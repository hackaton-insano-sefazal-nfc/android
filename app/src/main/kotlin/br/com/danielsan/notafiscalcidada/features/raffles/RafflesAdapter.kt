package br.com.danielsan.notafiscalcidada.features.raffles

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.danielsan.notafiscalcidada.databinding.ItemRaffleBinding
import br.com.danielsan.notafiscalcidada.domain.raffle.Raffle
import br.com.danielsan.notafiscalcidada.shared.recyclerview.BasicListAdapter

/**
 * Created by daniel on 01/10/17.
 */
class RafflesAdapter(private val presenter: RafflesPresenter) : BasicListAdapter<Raffle, RaffleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaffleViewHolder {
        return RaffleViewHolder(ItemRaffleBinding.inflate(LayoutInflater.from(parent.context), parent, false), presenter)
    }

}

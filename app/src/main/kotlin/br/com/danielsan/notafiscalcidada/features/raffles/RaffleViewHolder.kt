package br.com.danielsan.notafiscalcidada.features.raffles

import br.com.danielsan.notafiscalcidada.Constants
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.databinding.ItemRaffleBinding
import br.com.danielsan.notafiscalcidada.domain.raffle.Raffle
import br.com.danielsan.notafiscalcidada.shared.recyclerview.BasicViewHolder

/**
 * Created by daniel on 01/10/17.
 */
class RaffleViewHolder(
    private val binding: ItemRaffleBinding,
    private val presenter: RafflesPresenter
) : BasicViewHolder<Raffle>(binding.root) {

    private var raffle: Raffle? = null

    init {
        binding.root.setOnClickListener {
            raffle?.let { presenter.onClickRaffle(it) }
        }
    }

    override fun bind(obj: Raffle) {
        raffle = obj
        binding.apply {
            description.text = obj.description
            title.text = root.context.getString(R.string.title_dialog_raffle, obj.code)
            val dateOfRealization = obj.dateOfRealization
            date.text = if (dateOfRealization != null) {
                Constants.DATE_FORMAT.format(dateOfRealization)
            } else {
                "--/--/--"
            }

            executePendingBindings()
        }
    }

}

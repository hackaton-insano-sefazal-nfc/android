package br.com.danielsan.notafiscalcidada.features.adopt

import br.com.danielsan.notafiscalcidada.Constants
import br.com.danielsan.notafiscalcidada.databinding.ItemSocialEntityBinding
import br.com.danielsan.notafiscalcidada.domain.adopt.SocialEntity
import br.com.danielsan.notafiscalcidada.shared.recyclerview.BasicViewHolder
import br.com.jansenfelipe.androidmask.Utils

/**
 * Created by daniel on 01/10/17.
 */
class SocialEntityViewHolder(
        private val binding: ItemSocialEntityBinding,
        private val presenter: AdoptPresenter
) : BasicViewHolder<SocialEntity>(binding.root) {

    private var socialEntity: SocialEntity? = null

    init {
        binding.root.setOnClickListener { socialEntity?.let { presenter.onClickSocialEntity(it) } }
    }

    override fun bind(obj: SocialEntity) {
        socialEntity = obj
        binding.title.text = obj.socialName
        binding.cnpj.text = Utils.mask(Constants.CNPJ_MASK, obj.cnpj)
        binding.executePendingBindings()
    }

}

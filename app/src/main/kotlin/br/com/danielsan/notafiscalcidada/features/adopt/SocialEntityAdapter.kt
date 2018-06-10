package br.com.danielsan.notafiscalcidada.features.adopt

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.danielsan.notafiscalcidada.databinding.ItemSocialEntityBinding
import br.com.danielsan.notafiscalcidada.domain.adopt.SocialEntity
import br.com.danielsan.notafiscalcidada.shared.recyclerview.BasicListAdapter

/**
 * Created by daniel on 01/10/17.
 */
class SocialEntityAdapter(private val presenter: AdoptPresenter) : BasicListAdapter<SocialEntity, SocialEntityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialEntityViewHolder {
        return SocialEntityViewHolder(ItemSocialEntityBinding.inflate(LayoutInflater.from(parent.context), parent, false), presenter)
    }

}

package br.com.danielsan.notafiscalcidada.features.adopt

import br.com.danielsan.notafiscalcidada.base.BaseView
import br.com.danielsan.notafiscalcidada.domain.adopt.SocialEntity

/**
 * Created by daniel on 01/10/17.
 */
interface AdoptContract : BaseView {

    fun showSocialEntities(socialEntityList: List<SocialEntity>)

    fun showAdoptConfirmation(socialEntity: SocialEntity)

    fun navigateToAdoptFinished(socialEntity: SocialEntity)

}

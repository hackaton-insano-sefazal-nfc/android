package br.com.danielsan.notafiscalcidada.di

import br.com.danielsan.notafiscalcidada.features.adopt.AdoptActivity
import br.com.danielsan.notafiscalcidada.features.authentication.AuthActivity
import br.com.danielsan.notafiscalcidada.features.invoices.InvoicesActivity
import br.com.danielsan.notafiscalcidada.features.main.MainActivity
import br.com.danielsan.notafiscalcidada.features.raffles.RafflesActivity
import br.com.danielsan.notafiscalcidada.features.report.SignUpReportActivity
import br.com.danielsan.notafiscalcidada.features.report.list.ReportsActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

/**
 * Created by daniel on 23/09/17.
 */
@Module(includes = arrayOf(AndroidInjectionModule::class))
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class, RepositoryModule::class))
    internal abstract fun bindAuthActivity(): AuthActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class, RepositoryModule::class))
    internal abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class, RepositoryModule::class))
    internal abstract fun bindReportsActivity(): ReportsActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class, RepositoryModule::class))
    internal abstract fun bindSignUpReportActivity(): SignUpReportActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class, RepositoryModule::class))
    internal abstract fun bindSignUpRafflesActivity(): RafflesActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class, RepositoryModule::class))
    internal abstract fun bindAdoptActivity(): AdoptActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class, RepositoryModule::class))
    internal abstract fun bindInvoicesActivity(): InvoicesActivity

}

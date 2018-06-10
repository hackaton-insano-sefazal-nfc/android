package br.com.danielsan.notafiscalcidada.di

import android.os.Build
import br.com.danielsan.notafiscalcidada.data.persistence.Preferences
import br.com.danielsan.notafiscalcidada.domain.adopt.AdoptRepository
import br.com.danielsan.notafiscalcidada.domain.auth.AuthRepository
import br.com.danielsan.notafiscalcidada.domain.invoice.InvoiceRepository
import br.com.danielsan.notafiscalcidada.domain.raffle.RaffleRepository
import br.com.danielsan.notafiscalcidada.domain.report.ReportRepository
import br.com.danielsan.notafiscalcidada.domain.user.UserRepository
import br.com.danielsan.notafiscalcidada.features.adopt.AdoptPresenter
import br.com.danielsan.notafiscalcidada.features.authentication.AuthPresenter
import br.com.danielsan.notafiscalcidada.features.invoices.InvoicesPresenter
import br.com.danielsan.notafiscalcidada.features.main.MainPresenter
import br.com.danielsan.notafiscalcidada.features.raffles.RafflesPresenter
import br.com.danielsan.notafiscalcidada.features.report.SignUpReportPresenter
import br.com.danielsan.notafiscalcidada.features.report.list.ReportsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by daniel on 26/09/17.
 */
@Module
class PresenterModule {

    @PerActivity
    @Provides
    internal fun provideAuthPresenter(authRepository: AuthRepository, preferences: Preferences): AuthPresenter {
        return AuthPresenter(authRepository, preferences, Build.MODEL)
    }

    @PerActivity
    @Provides
    internal fun provideMainPresenter(userRepository: UserRepository, preferences: Preferences): MainPresenter {
        return MainPresenter(userRepository, preferences)
    }

    @PerActivity
    @Provides
    internal fun provideReportsPresenter(reportsRepository: ReportRepository): ReportsPresenter {
        return ReportsPresenter(reportsRepository)
    }

    @PerActivity
    @Provides
    internal fun provideSignUpReportPresenter(reportsRepository: ReportRepository): SignUpReportPresenter {
        return SignUpReportPresenter(reportsRepository)
    }

    @PerActivity
    @Provides
    internal fun provideRafflesPresenter(raffleRepository: RaffleRepository): RafflesPresenter {
        return RafflesPresenter(raffleRepository)
    }

    @PerActivity
    @Provides
    internal fun provideDonationPresenter(adoptRepository: AdoptRepository): AdoptPresenter {
        return AdoptPresenter(adoptRepository)
    }

    @PerActivity
    @Provides
    internal fun provideInvoicesPresenter(invoiceRepository: InvoiceRepository): InvoicesPresenter {
        return InvoicesPresenter(invoiceRepository)
    }

}

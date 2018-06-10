package br.com.danielsan.notafiscalcidada.di

import br.com.danielsan.notafiscalcidada.domain.adopt.AdoptRepository
import br.com.danielsan.notafiscalcidada.domain.adopt.FakeAdoptRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.auth.AuthRepository
import br.com.danielsan.notafiscalcidada.domain.auth.FakeAuthRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.invoice.FakeInvoiceRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.invoice.InvoiceRepository
import br.com.danielsan.notafiscalcidada.domain.raffle.FakeRaffleRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.raffle.RaffleRepository
import br.com.danielsan.notafiscalcidada.domain.ranking.FakeRankingRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.ranking.RankingRepository
import br.com.danielsan.notafiscalcidada.domain.report.FakeReportRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.report.ReportRepository
import br.com.danielsan.notafiscalcidada.domain.user.FakeUserRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.user.UserRepository
import dagger.Module
import dagger.Provides

/**
 * Created by daniel on 24/09/17.
 */
@Module
class FakeRepositoryModule {

    @Provides
    fun provideAuthRepository(): AuthRepository {
        return FakeAuthRepositoryImpl()
    }

    @Provides
    fun provideUserRepository(): UserRepository {
        return FakeUserRepositoryImpl()
    }

    @Provides
    fun provideReportsRepository(): ReportRepository {
        return FakeReportRepositoryImpl()
    }

    @Provides
    fun provideRaffleRepository(): RaffleRepository {
        return FakeRaffleRepositoryImpl()
    }

    @Provides
    fun provideAdoptRepository(): AdoptRepository {
        return FakeAdoptRepositoryImpl()
    }

    @Provides
    fun provideInvoiceRepository(): InvoiceRepository {
        return FakeInvoiceRepositoryImpl()
    }

    @Provides
    fun provideRankingRepository(): RankingRepository {
        return FakeRankingRepositoryImpl()
    }

}

package br.com.danielsan.notafiscalcidada.di

import br.com.danielsan.notafiscalcidada.domain.adopt.AdoptRepository
import br.com.danielsan.notafiscalcidada.domain.adopt.AdoptRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.auth.AuthRepository
import br.com.danielsan.notafiscalcidada.domain.auth.AuthRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.invoice.InvoiceRepository
import br.com.danielsan.notafiscalcidada.domain.invoice.InvoiceRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.raffle.RaffleRepository
import br.com.danielsan.notafiscalcidada.domain.raffle.RaffleRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.ranking.RankingRepository
import br.com.danielsan.notafiscalcidada.domain.ranking.RankingRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.report.ReportRepository
import br.com.danielsan.notafiscalcidada.domain.report.ReportRepositoryImpl
import br.com.danielsan.notafiscalcidada.domain.user.UserRepository
import br.com.danielsan.notafiscalcidada.domain.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by daniel on 24/09/17.
 */
@Module
class RepositoryModule {

    @Provides
    internal fun provideAuthRepository(@Named("sefaz_retrofit") retrofit: Retrofit,
                                       @Named("sefaz_token") sefazToken: String): AuthRepository {
        return AuthRepositoryImpl(retrofit, sefazToken)
    }

    @Provides
    internal fun provideUserRepository(@Named("authorization_sefaz_retrofit") retrofit: Retrofit,
                                       raffleRepository: RaffleRepository,
                                       invoiceRepository: InvoiceRepository): UserRepository {
        return UserRepositoryImpl(retrofit, raffleRepository, invoiceRepository)
    }

    @Provides
    internal fun provideReportsRepository(@Named("authorization_sefaz_retrofit") sefazRetrofit: Retrofit): ReportRepository {
        return ReportRepositoryImpl(sefazRetrofit)
    }

    @Provides
    internal fun provideRafflesRepository(@Named("authorization_sefaz_retrofit") retrofit: Retrofit): RaffleRepository {
        return RaffleRepositoryImpl(retrofit)
    }

    @Provides
    internal fun provideAdoptRepository(@Named("authorization_sefaz_retrofit") retrofit: Retrofit): AdoptRepository {
        return AdoptRepositoryImpl(retrofit)
    }

    @Provides
    internal fun provideInvoiceRepository(@Named("authorization_sefaz_retrofit") retrofit: Retrofit): InvoiceRepository {
        return InvoiceRepositoryImpl(retrofit)
    }

    // TODO: Use app retrofit when we have a backend
    @Provides
    internal fun provideRankingRepository(@Named("authorization_sefaz_retrofit") retrofit: Retrofit): RankingRepository {
//    internal fun provideRankingRepository(@Named("authorization_app_retrofit") retrofit: Retrofit): RankingRepository {
        return RankingRepositoryImpl(retrofit)
    }

}

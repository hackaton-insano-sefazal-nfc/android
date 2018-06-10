package br.com.danielsan.notafiscalcidada.di

import android.content.Context
import br.com.danielsan.notafiscalcidada.Application
import br.com.danielsan.notafiscalcidada.R
import br.com.danielsan.notafiscalcidada.data.persistence.Preferences
import br.com.danielsan.notafiscalcidada.data.persistence.SharedPreferences
import br.com.danielsan.notafiscalcidada.domain.TokenInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import com.google.gson.Gson



/**
 * Created by daniel on 23/09/17.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun providePreferences(application: Application): Preferences = SharedPreferences(application)

    @Named("sefaz_endpoint")
    @Provides
    @Singleton
    internal fun provideSefazEndpointUrl(application: Application): String {
        return application.getString(R.string.sefaz_url)
    }

    @Named("sefaz_token")
    @Provides
    @Singleton
    internal fun provideSefazToken(application: Application): String {
        return application.getString(R.string.sefaz_token)
    }

    @Named("client")
    @Provides
    @Singleton
    internal fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Named("authorization_client")
    @Provides
    @Singleton
    internal fun provideAuthorizationClient(preferences: Preferences): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(TokenInterceptor(preferences))
                .build()
    }

    @Named("sefaz_retrofit")
    @Provides
    @Singleton
    internal fun provideSefazRetrofit(@Named("client") client: OkHttpClient,
                                      @Named("sefaz_endpoint") sefazUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(sefazUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Named("authorization_sefaz_retrofit")
    @Provides
    @Singleton
    internal fun provideAuthorizationSefazRetrofit(@Named("authorization_client") client: OkHttpClient,
                                                   @Named("sefaz_endpoint") sefazUrl: String): Retrofit {

        val gson = GsonBuilder()
                .setLenient()
                .create()

        return Retrofit.Builder()
                .baseUrl(sefazUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}

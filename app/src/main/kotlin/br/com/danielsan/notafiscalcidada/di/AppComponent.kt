package br.com.danielsan.notafiscalcidada.di

import br.com.danielsan.notafiscalcidada.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by daniel on 23/09/17.
 */
@Singleton
@Component(modules = arrayOf(
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class
))
interface AppComponent : AndroidInjector<Application> {

    override fun inject(app: Application)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}

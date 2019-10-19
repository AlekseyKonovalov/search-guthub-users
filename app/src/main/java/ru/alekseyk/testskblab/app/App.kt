package ru.alekseyk.testskblab.app

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.alekseyk.testskblab.data.datasource.dataSourceModule
import ru.alekseyk.testskblab.data.db.databaseModule
import ru.alekseyk.testskblab.data.network.retrofitModule
import ru.alekseyk.testskblab.data.prefs.appPrefsModule
import ru.alekseyk.testskblab.data.repository.repositoryModule
import ru.alekseyk.testskblab.domain.usecase.useCaseModule
import ru.alekseyk.testskblab.presentation.screen.auth.authModule
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appPrefsModule,
                    databaseModule,
                    dataSourceModule,
                    repositoryModule,
                    retrofitModule,

                    useCaseModule,
/*
                    repoListModule,
                    detailModule,*/
                    authModule
                )
            )
        }

    }

}
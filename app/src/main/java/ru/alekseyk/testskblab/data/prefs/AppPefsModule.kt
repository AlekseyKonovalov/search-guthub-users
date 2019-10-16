package ru.alekseyk.testskblab.data.prefs

import org.koin.dsl.module

val appPrefsModule = module {
    single { AppPrefs(get()) }
}
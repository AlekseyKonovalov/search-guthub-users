package ru.alekseyk.testskblab.data.datasource

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.alekseyk.testskblab.data.datasource.local.LocalDataSource
import ru.alekseyk.testskblab.data.datasource.remote.RemoteDataSource

val dataSourceModule = module {

    single<IDataSource>(named("local")) { LocalDataSource(get()) }

    single<IDataSource>(named("remote")) { RemoteDataSource(get()) }

}
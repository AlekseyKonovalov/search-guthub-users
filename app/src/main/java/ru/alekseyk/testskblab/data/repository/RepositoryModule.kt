package ru.alekseyk.testskblab.data.repository

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.alekseyk.testskblab.domain.repository.IRepository


val repositoryModule = module {

    single<IRepository>{ Repository(get(named("local")), get(named("remote"))) }

}
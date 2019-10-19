package ru.alekseyk.testskblab.domain.usecase

import org.koin.dsl.module

val useCaseModule = module {
    factory { UserUseCase(get()) }
    factory { RepositoriesUseCase(get()) }
}
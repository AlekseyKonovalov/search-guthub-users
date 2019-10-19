package ru.alekseyk.testskblab.presentation.screen.repo_list.favorites_repo

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesRepoModule = module {
    viewModel { FavoritesRepoViewModel(get()) }
}
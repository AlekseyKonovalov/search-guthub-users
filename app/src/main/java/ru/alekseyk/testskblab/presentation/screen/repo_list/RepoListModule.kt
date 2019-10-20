package ru.alekseyk.testskblab.presentation.screen.repo_list

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoListModule= module {
    viewModel { RepoListViewModel(get()) }
}
package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchRepoModule = module {
    viewModel { SearchRepoViewModel(get()) }
}
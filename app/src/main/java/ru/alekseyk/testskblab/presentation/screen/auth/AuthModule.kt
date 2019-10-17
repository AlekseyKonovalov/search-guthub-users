package ru.alekseyk.testskblab.presentation.screen.auth

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule= module {
    viewModel { AuthViewModel() }
}
package ru.alekseyk.testskblab.presentation.screen.auth

internal data class AuthViewState(
    val isLoading: Boolean = false,
    val isFinish: Boolean = false,
    val accountName: String = ""
)
package ru.alekseyk.testskblab.presentation.utils

sealed class LoadingState<out T> {
    class Loading<out T> : LoadingState<T>()
    class Success<out T>(val data: T) : LoadingState<T>()
}
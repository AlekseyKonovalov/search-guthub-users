package ru.alekseyk.testskblab.presentation.screen.auth

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.domain.usecase.UserUseCase
import ru.alekseyk.testskblab.presentation.base.StateViewModel
import timber.log.Timber

class AuthViewModel(
    private val userUseCase: UserUseCase
) : StateViewModel<AuthViewState>(
    defaultState = AuthViewState()
) {

    fun setUserData(accountName: String) {
        userUseCase.setUserData(accountName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { updateState(currentState.copy(isLoading = true)) }
            .subscribeBy(
                onComplete = {
                    updateState(currentState.copy(isLoading = false, isFinish = true, accountName = accountName))
                },
                onError = { error ->
                    Timber.e(error)
                    updateState(currentState.copy(isLoading = false))
                }
            ).addTo(disposables)
    }


    fun getCurrentUserData() {
        userUseCase.getCurrentUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { updateState(currentState.copy(isLoading = true)) }
            .subscribeBy(
                onSuccess = {
                    if (it.isNullOrEmpty()) {
                        updateState(currentState.copy(isLoading = false, isFinish = false))
                    } else {
                        updateState(currentState.copy(isLoading = false, isFinish = true, accountName = it))
                    }
                },
                onError = { error ->
                    Timber.e(error)
                    updateState(currentState.copy(isLoading = false))
                }
            ).addTo(disposables)
    }

}
package ru.alekseyk.testskblab.presentation.screen.repo_list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.domain.usecase.UserUseCase
import ru.alekseyk.testskblab.presentation.base.StateViewModel

internal class RepoListViewModel(
    private var userUseCase: UserUseCase
) : StateViewModel<RepoListViewState>(
    defaultState = RepoListViewState()
) {


    fun deleteUserData() {
        userUseCase.deleteUserData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { updateState(currentState.copy(isLoading = true)) }
            .subscribeBy(
                onComplete = { updateState(currentState.copy(isLoading = false, isFinish = true)) },
                onError = { updateState(currentState.copy(isLoading = false)) }
            )
            .addTo(disposables)
    }

}
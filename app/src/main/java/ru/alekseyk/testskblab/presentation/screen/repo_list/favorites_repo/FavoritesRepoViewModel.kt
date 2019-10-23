package ru.alekseyk.testskblab.presentation.screen.repo_list.favorites_repo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.domain.usecase.RepositoriesUseCase
import ru.alekseyk.testskblab.presentation.base.StateViewModel
import ru.alekseyk.testskblab.presentation.mapper.PresentationMapper

class FavoritesRepoViewModel(
    private val repositoriesUseCase: RepositoriesUseCase
) : StateViewModel<FavoritesRepoViewState>(
    defaultState = FavoritesRepoViewState()
) {


    fun getFavoritesRepositories() {
        repositoriesUseCase.getFavoritesRepositories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { updateState(currentState.copy(isLoading = true)) }
            .subscribeBy(
                onSuccess = {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            payload = it.map { PresentationMapper.toRepositoryModel(it) })
                    )
                },
                onError = { updateState(currentState.copy(isLoading = false)) }
            )
            .addTo(disposables)
    }

}
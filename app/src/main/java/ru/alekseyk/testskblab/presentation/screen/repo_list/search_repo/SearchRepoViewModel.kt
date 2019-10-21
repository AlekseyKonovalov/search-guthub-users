package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.domain.usecase.RepositoriesUseCase
import ru.alekseyk.testskblab.presentation.base.StateViewModel
import ru.alekseyk.testskblab.presentation.mapper.PresentationMapper
import ru.alekseyk.testskblab.presentation.models.RepositoryModel
import timber.log.Timber
import java.util.concurrent.TimeUnit

internal class SearchRepoViewModel(
    private val repositoriesUseCase: RepositoriesUseCase
) : StateViewModel<SearchRepoViewState>(
    defaultState = SearchRepoViewState()
) {

    fun updateFavoriteStatus(repositoryModel: RepositoryModel, status: Boolean) {
        repositoriesUseCase.updateFavoriteStatus(
            PresentationMapper.toRepositoryEntity(
                repositoryModel.copy(isFavorite = status)
            )
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = { },
                onError = { Timber.e(it) }
            )
            .addTo(disposables)
    }

    fun updateSearchQuery(query: String) {
        Observable.just(query)
            .map { text -> text.toLowerCase().trim() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinct()
            .subscribeBy(onNext = {
                updateState(currentState.copy(searchQuery = query))
                requestData()
            })
            .addTo(disposables)


/*        updateState(currentState.copy(searchQuery = query))
*//*        if (query.isBlank()) {
            updateState(currentState.copy(payload = emptyList(), isLoading = false))
            return
        }*//*
        requestData()*/
    }

    fun requestData() {
        if(currentState.searchQuery.isNullOrEmpty()){
            updateState(
                currentState.copy(
                    isLoading = false,
                    payload = listOf())
            )
            return
        }

        repositoriesUseCase.getRepositoriesBySearch(currentState.searchQuery)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { updateState(currentState.copy(isLoading = true)) }
            .subscribeBy(
                onNext = {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            payload = it.map { PresentationMapper.toRepositoryModel(it) })
                    )
                },
                onError = {
                    Timber.e(it)
                    updateState(currentState.copy(isLoading = false))
                }
            )
            .addTo(disposables)
    }


}
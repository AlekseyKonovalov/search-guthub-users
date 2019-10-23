package ru.alekseyk.testskblab.presentation.screen.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.domain.usecase.RepositoriesUseCase
import ru.alekseyk.testskblab.presentation.base.StateViewModel
import ru.alekseyk.testskblab.presentation.mapper.PresentationMapper
import ru.alekseyk.testskblab.presentation.models.RepositoryModel
import timber.log.Timber

class DetailViewModel(
    private val repositoriesUseCase: RepositoriesUseCase
) : StateViewModel<DetailViewState>(
    defaultState = DetailViewState()
) {
    fun updateFavoriteStatus(repositoryModel: RepositoryModel) {
        if (currentState.isFavorite) {
            updateState(currentState.copy(isFavorite = false))
            repositoriesUseCase.deleteFromFavoritesRepositories(
                PresentationMapper.toRepositoryEntity(
                    repositoryModel.copy(isFavorite = false)
                )
            )


        } else {
            updateState(currentState.copy(isFavorite = true))
            repositoriesUseCase.addToFavoritesRepositories(
                PresentationMapper.toRepositoryEntity(
                    repositoryModel.copy(isFavorite = true)
                )
            )

        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = { },
                onError = { Timber.e(it) }
            )
            .addTo(disposables)
    }

    fun setStatus(repositoryModel: RepositoryModel) {
        repositoriesUseCase.getFavoriteRepositoryById(repositoryModel.id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    it?.let {
                        updateState(currentState.copy(isFavorite = true))
                    } ?: run {
                        updateState(currentState.copy(isFavorite = false))
                    }
                },
                onError = {
                    Timber.e(it)
                    updateState(currentState.copy(isFavorite = repositoryModel.isFavorite))
                }
            )
            .addTo(disposables)
    }
}
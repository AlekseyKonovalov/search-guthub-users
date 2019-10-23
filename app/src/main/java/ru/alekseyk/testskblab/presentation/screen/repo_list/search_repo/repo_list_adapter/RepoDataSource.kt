package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.repo_list_adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.domain.entity.RepositoryEntity
import ru.alekseyk.testskblab.domain.usecase.RepositoriesUseCase
import ru.alekseyk.testskblab.presentation.mapper.PresentationMapper
import ru.alekseyk.testskblab.presentation.models.RepositoryModel
import ru.alekseyk.testskblab.presentation.utils.LoadingState
import timber.log.Timber


class RepoDataSource(
    private val repositoriesUseCase: RepositoriesUseCase,
    private val disposables: CompositeDisposable,
    private val query: String
) : PageKeyedDataSource<Long, RepositoryModel>() {
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, RepositoryModel>
    ) {
        disposables += repositoriesUseCase.getRepositoriesBySearch(
            query,
            1,
            params.requestedLoadSize
        )
            .subscribeOn(Schedulers.io())
            .flatMap {
                Observable.just<LoadingState<List<RepositoryEntity>>>(LoadingState.Success(it))
            }
            .startWith(LoadingState.Loading())
            .subscribe(
                { response ->
                    when (response) {
                        is LoadingState.Success -> {
                            loadingState.postValue(PagingLoadingState.DONE)
                            callback.onResult(response.data.map {
                                PresentationMapper.toRepositoryModel(
                                    it
                                )
                            }, null, 2L)
                        }
                    }
                },
                { error ->
                    loadingState.postValue(PagingLoadingState.ERROR)
                    Timber.e(error)
                    retry = { loadInitial(params, callback) }
                }
            )
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, RepositoryModel>
    ) {
        disposables += repositoriesUseCase.getRepositoriesBySearch(
            query,
            params.key.toInt(),
            params.requestedLoadSize
        )
            .subscribeOn(Schedulers.io())
            .flatMap {
                Observable.just<LoadingState<List<RepositoryEntity>>>(LoadingState.Success(it))
            }
            .startWith(LoadingState.Loading())
            .subscribe(
                { response ->
                    when (response) {
                        is LoadingState.Success -> {
                            loadingState.postValue(PagingLoadingState.DONE)

                            callback.onResult(response.data.map {
                                PresentationMapper.toRepositoryModel(
                                    it
                                )
                            }, if ((params.key + 1) < 11) params.key + 1 else null)


                        }
                    }
                },
                { error ->
                    loadingState.postValue(PagingLoadingState.ERROR)
                    Timber.e(error)
                }
            )
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, RepositoryModel>
    ) {
    }


    var retry: (() -> Unit)? = null

    val loadingState = MutableLiveData<PagingLoadingState>()

    init {
        loadingState.postValue(PagingLoadingState.LOADING)
    }


}

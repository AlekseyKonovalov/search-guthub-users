package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.domain.usecase.RepositoriesUseCase
import ru.alekseyk.testskblab.presentation.base.StateViewModel
import ru.alekseyk.testskblab.presentation.models.RepositoryModel
import ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.repo_list_adapter.RepoDataSourceFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


class SearchRepoViewModel(
    private val repositoriesUseCase: RepositoriesUseCase
) : StateViewModel<SearchRepoViewState>(
    defaultState = SearchRepoViewState()
) {

    private val paginConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10)
        .setPageSize(10)
        .build()

    private lateinit var sourceFactory: RepoDataSourceFactory
    private lateinit var searchItems: Flowable<PagedList<RepositoryModel>>

    private fun initPaging(query: String) {

        sourceFactory = RepoDataSourceFactory(repositoriesUseCase, disposables, query)
        searchItems = RxPagedListBuilder(sourceFactory, paginConfig)
            .setFetchScheduler(Schedulers.io())
            .buildFlowable(BackpressureStrategy.LATEST)

        sourceFactory.dataSource.loadingState.observeForever { state ->
            state?.let {
                updateState(
                    currentState.copy(
                        pagingLoadingState = it
                    )
                )
            }
        }

    }

    fun updateSearchQuery(query: String) {

        Observable.just(query)
            .map { text -> text.toLowerCase().trim() }
            .distinct()
            .subscribeBy(onNext = {
                updateState(currentState.copy(searchQuery = query))
                requestData()
            })
            .addTo(disposables)
    }

    fun requestData() {
        if (currentState.searchQuery.isNullOrEmpty()) {
            updateState(
                currentState.copy(
                    isLoading = false,
                    searchItems = null
                )
            )
            return
        }

        initPaging(currentState.searchQuery)

        searchItems
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                updateState(
                    currentState.copy(
                        isLoading = true
                    )
                )
            }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            searchItems = it
                        )
                    )
                },
                onError = {
                    Timber.e(it)
                    updateState(currentState.copy(isLoading = false))
                }
            )
            .addTo(disposables)
    }

    fun invalidateDataSource(position: Int) {
        sourceFactory.dataSource.invalidate()
    }

    fun onPagingRetryBtnClickListener() {
        sourceFactory.dataSource.retry?.invoke()
    }


}
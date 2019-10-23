package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.repo_list_adapter

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import ru.alekseyk.testskblab.domain.usecase.RepositoriesUseCase
import ru.alekseyk.testskblab.presentation.models.RepositoryModel


class RepoDataSourceFactory(
    repositoriesUseCase: RepositoriesUseCase,
    disposables: CompositeDisposable,
    query: String
) : DataSource.Factory<Int, RepositoryModel>() {

    val dataSource = RepoDataSource(repositoriesUseCase, disposables, query)


    override fun create(): DataSource<Int, RepositoryModel> {
        return dataSource as DataSource<Int, RepositoryModel>
    }


}
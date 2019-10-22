package ru.alekseyk.testskblab.data.datasource.remote

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.dto.SearchRepositoriesListDto
import ru.alekseyk.testskblab.data.network.Api


class RemoteDataSource(private val api: Api) : IDataSource {
    override fun deleteFromFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable {
        return Completable.error(Exception("Method only for LocalDataSource realization"))
    }

    override fun addToFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable {
        return Completable.error(Exception("Method only for LocalDataSource realization"))
    }

    override fun deleteUserData(): Completable {
        return Completable.error(Exception("Method only for LocalDataSource realization"))
    }

    override fun getFavoritesRepositories(): Single<List<RepositoryDbEntity>> {
        return Single.error(Exception("Method only for LocalDataSource realization"))
    }

    override fun getRepositoriesBySearch(query: String): Observable<SearchRepositoriesListDto> {
        return api.searchRepositoriesByQuery(query)
    }

    override fun getCurrentUserData(): Single<String> {
        return Single.error(Exception("Method only for LocalDataSource realization"))
    }

    override fun setUserData(accountEmail: String): Completable {
        return Completable.error(Exception("Method only for LocalDataSource realization"))
    }


}
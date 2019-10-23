package ru.alekseyk.testskblab.data.datasource

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.dto.SearchRepositoriesListDto


interface IDataSource {
    fun setUserData(accountEmail : String): Completable
    fun getCurrentUserData(): Single<String>
    fun getRepositoriesBySearch(query: String, pageSize: Int, position: Int): Observable<SearchRepositoriesListDto>
    fun getFavoritesRepositories(): Single<List<RepositoryDbEntity>>
    fun addToFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable
    fun deleteFromFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable
    fun deleteUserData(): Completable
    fun getFavoriteRepositoryById(repositoryId: Int): Single<RepositoryDbEntity?>
}
package ru.alekseyk.testskblab.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity

interface IRepository {
    fun setUserData(accountEmail: String): Completable
    fun getCurrentUserData(): Single<String>
    fun getRepositoriesBySearch(query: String, pageSize: Int, position: Int): Observable<List<RepositoryDbEntity>>
    fun addToFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable
    fun deleteFromFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable
    fun getFavoritesRepositories(): Single<List<RepositoryDbEntity>>
    fun deleteUserData(): Completable
    fun getFavoriteRepositoryById(repositoryId: Int): Single<RepositoryDbEntity?>
}
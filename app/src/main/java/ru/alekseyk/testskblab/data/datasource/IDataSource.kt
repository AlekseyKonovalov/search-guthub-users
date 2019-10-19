package ru.alekseyk.testskblab.data.datasource

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.data.dto.SearchRepositoriesListDto


interface IDataSource {

    fun setUserData(userDbEntity : UserDbEntity): Completable
    fun getCurrentUserData(): Single<List<UserDbEntity>>

    fun getRepositoriesBySearch(query: String): Observable<SearchRepositoriesListDto>
    fun updateFavoriteStatus(repositoryEntity: RepositoryDbEntity): Completable

}
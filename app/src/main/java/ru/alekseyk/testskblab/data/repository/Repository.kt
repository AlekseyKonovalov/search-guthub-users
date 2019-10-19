package ru.alekseyk.testskblab.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.data.dto.SearchRepositoriesListDto
import ru.alekseyk.testskblab.domain.repository.IRepository

internal class Repository(
    private val localDataSource: IDataSource,
    private val remoteDataSource: IDataSource
) : IRepository {
    override fun updateFavoriteStatus(repositoryEntity: RepositoryDbEntity): Completable {
        return  localDataSource.updateFavoriteStatus(repositoryEntity)
    }

    override fun getRepositoriesBySearch(query: String): Observable<SearchRepositoriesListDto> {
        return remoteDataSource.getRepositoriesBySearch(query)
    }

    override fun getCurrentUserData(): Single<List<UserDbEntity>> {
        return localDataSource.getCurrentUserData()
    }

    override fun setUserData(userDbEntity: UserDbEntity): Completable {
        return localDataSource.setUserData(userDbEntity)
    }

}
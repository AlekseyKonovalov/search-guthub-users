package ru.alekseyk.testskblab.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.domain.repository.IRepository

internal class Repository(
    private val localDataSource: IDataSource,
    private val remoteDataSource: IDataSource
)  : IRepository {

    override fun getCurrentUserData(): Single<List<UserDbEntity>> {
        return localDataSource.getCurrentUserData()
    }

    override fun setUserData(userDbEntity: UserDbEntity): Completable {
        return localDataSource.setUserData(userDbEntity)
    }

}
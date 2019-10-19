package ru.alekseyk.testskblab.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.domain.repository.IRepository

internal class Repository(
    private val localDataSource: IDataSource,
    private val remoteDataSource: IDataSource
) : IRepository {

    override fun getRepositoriesBySearch(searchKey: String): Observable<List<RepositoryDbEntity>> {
        val mockdate: List<RepositoryDbEntity> = listOf(
            RepositoryDbEntity(
                id = 0,
                name = "0fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 10,
                name = "1fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 20,
                name = "2fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 30,
                name = "3fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 0,
                name = "0fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 10,
                name = "1fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 20,
                name = "2fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 30,
                name = "3fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 0,
                name = "0fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 10,
                name = "1fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 20,
                name = "2fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 30,
                name = "3fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 0,
                name = "0fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 10,
                name = "1fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 20,
                name = "2fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 30,
                name = "3fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 0,
                name = "0fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 10,
                name = "1fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 20,
                name = "2fdfdsfdsfsdfdsfds"
            ),
            RepositoryDbEntity(
                id = 30,
                name = "3fdfdsfdsfsdfdsfds"
            )
        )
        return Observable.fromCallable { mockdate }
    }

    override fun getCurrentUserData(): Single<List<UserDbEntity>> {
        return localDataSource.getCurrentUserData()
    }

    override fun setUserData(userDbEntity: UserDbEntity): Completable {
        return localDataSource.setUserData(userDbEntity)
    }

}
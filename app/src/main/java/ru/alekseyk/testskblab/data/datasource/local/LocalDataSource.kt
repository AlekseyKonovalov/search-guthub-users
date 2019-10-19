package ru.alekseyk.testskblab.data.datasource.local


import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.AppDatabase
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity

class LocalDataSource(private val appDatabase: AppDatabase) : IDataSource {
    override fun getRepositoriesBySearch(): Observable<List<RepositoryDbEntity>> {
        return Observable.error(Exception("Method only for RemoteDataSource realization"))
    }

    override fun getCurrentUserData(): Single<List<UserDbEntity>> {
        return appDatabase.userDao().getCurrentAccount()
    }

    override fun setUserData(userDbEntity : UserDbEntity): Completable {
        return appDatabase.userDao().insert(userDbEntity)
    }

}
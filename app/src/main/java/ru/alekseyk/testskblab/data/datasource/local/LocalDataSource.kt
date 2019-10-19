package ru.alekseyk.testskblab.data.datasource.local


import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.AppDatabase
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity

class LocalDataSource(private val appDatabase: AppDatabase) : IDataSource {

    override fun getCurrentUserData(): Single<List<UserDbEntity>> {
        return appDatabase.userDao().getCurrentAccount()
    }

    override fun setUserData(userDbEntity : UserDbEntity): Completable {
        return appDatabase.userDao().insert(userDbEntity)
    }

}
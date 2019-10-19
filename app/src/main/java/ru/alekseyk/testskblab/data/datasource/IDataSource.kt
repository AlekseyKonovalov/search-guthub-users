package ru.alekseyk.testskblab.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity


interface IDataSource {

    fun setUserData(userDbEntity : UserDbEntity): Completable
    fun getCurrentUserData(): Single<List<UserDbEntity>>

}
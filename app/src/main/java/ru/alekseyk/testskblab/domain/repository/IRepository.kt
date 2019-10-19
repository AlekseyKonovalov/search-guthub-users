package ru.alekseyk.testskblab.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity

interface IRepository {
    fun setUserData(userDbEntity: UserDbEntity): Completable
    fun getCurrentUserData(): Single<List<UserDbEntity>>
}
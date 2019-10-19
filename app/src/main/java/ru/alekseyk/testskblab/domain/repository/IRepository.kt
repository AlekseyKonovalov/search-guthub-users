package ru.alekseyk.testskblab.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity

interface IRepository {
    fun setUserData(accountName: String): Completable
    fun getCurrentUserData(): Single<List<UserDbEntity>>
}
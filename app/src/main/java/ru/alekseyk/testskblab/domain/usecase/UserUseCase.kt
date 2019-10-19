package ru.alekseyk.testskblab.domain.usecase

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.domain.repository.IRepository

class UserUseCase(
    private val repository: IRepository
) {

    fun setUserData(accountName: String): Completable {
        return repository.setUserData(accountName)
    }

    fun getCurrentUserData():Single<List<UserDbEntity>> {
        return repository.getCurrentUserData()
    }


}
package ru.alekseyk.testskblab.domain.usecase

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyk.testskblab.domain.repository.IRepository

class UserUseCase(
    private val repository: IRepository
) {

    fun deleteUserData(): Completable {
        return repository.deleteUserData()
    }

    fun setUserData(accountEmail: String): Completable {
        return repository.setUserData(accountEmail)
    }

    fun getCurrentUserData():Single<String> {
        return repository.getCurrentUserData()
    }


}
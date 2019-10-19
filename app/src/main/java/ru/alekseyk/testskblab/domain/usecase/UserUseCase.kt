package ru.alekseyk.testskblab.domain.usecase

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.domain.entity.UserEntity
import ru.alekseyk.testskblab.domain.mapper.DomainMapper
import ru.alekseyk.testskblab.domain.repository.IRepository

class UserUseCase(
    private val repository: IRepository
) {

    fun setUserData(userEntity: UserEntity): Completable {
        return repository.setUserData(DomainMapper.toUserDbEntity(userEntity))
    }

    fun getCurrentUserData():Single<List<UserDbEntity>> {
        return repository.getCurrentUserData()
    }


}
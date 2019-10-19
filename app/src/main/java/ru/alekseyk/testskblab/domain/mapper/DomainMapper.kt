package ru.alekseyk.testskblab.domain.mapper

import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.domain.entity.RepositoryEntity
import ru.alekseyk.testskblab.domain.entity.UserEntity

internal object DomainMapper {
    fun toUserDbEntity(userEntity: UserEntity): UserDbEntity {
        return UserDbEntity(accountEmail = userEntity.accountEmail, isSignIn = userEntity.isSignIn)
    }

    fun toRepositoryEntity(repositoryDbEntity: RepositoryDbEntity): RepositoryEntity {
        return RepositoryEntity(name = repositoryDbEntity.name)
    }
}
package ru.alekseyk.testskblab.domain.mapper

import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.domain.entity.RepositoryEntity
import ru.alekseyk.testskblab.domain.entity.UserEntity

internal object DomainMapper {
    fun toUserDbEntity(userEntity: UserEntity): UserDbEntity {
        return UserDbEntity(accountEmail = userEntity.accountEmail, isSignIn = userEntity.isSignIn)
    }

    fun toRepositoryEntity(
        description: String?,
        fullName: String,
        id: Int,
        name: String,
        ownerId: Int,
        ownerLogin: String,
        ownerAvatarUrl: String,
        ownerUrl: String
    ): RepositoryEntity {
        return RepositoryEntity(
            description = description,
            fullName = fullName,
            id = id,
            name = name,
            ownerId = ownerId,
            ownerLogin = ownerLogin,
            ownerAvatarUrl = ownerAvatarUrl,
            ownerUrl = ownerUrl
        )

    }
}
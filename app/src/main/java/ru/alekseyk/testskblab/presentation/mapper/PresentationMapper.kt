package ru.alekseyk.testskblab.presentation.mapper

import ru.alekseyk.testskblab.domain.entity.RepositoryEntity
import ru.alekseyk.testskblab.domain.entity.UserEntity
import ru.alekseyk.testskblab.presentation.models.RepositoryModel


internal object PresentationMapper {
    fun toUserEntity(accountName: String, isSignIn: Boolean = false): UserEntity {
        return UserEntity(accountName, isSignIn)
    }

    fun toRepositoryModel(repositoryEntity: RepositoryEntity): RepositoryModel {
        return RepositoryModel(
            description = repositoryEntity.description,
            fullName = repositoryEntity.fullName,
            id = repositoryEntity.id,
            name = repositoryEntity.name,
            ownerId = repositoryEntity.ownerId,
            ownerLogin = repositoryEntity.ownerLogin,
            ownerAvatarUrl = repositoryEntity.ownerAvatarUrl,
            ownerUrl = repositoryEntity.ownerUrl
        )
    }
}
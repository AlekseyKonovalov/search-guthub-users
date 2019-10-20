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
            ownerUrl = repositoryEntity.ownerUrl,
            isFavorite = repositoryEntity.isFavorite,
            stargazersCount = repositoryEntity.stargazersCount,
            forksCount = repositoryEntity.forksCount,
            createdAt = repositoryEntity.createdAt
        )
    }

    fun toRepositoryEntity(repositoryModel: RepositoryModel): RepositoryEntity {
        return RepositoryEntity(
            description = repositoryModel.description,
            fullName = repositoryModel.fullName,
            id = repositoryModel.id,
            name = repositoryModel.name,
            ownerId = repositoryModel.ownerId,
            ownerLogin = repositoryModel.ownerLogin,
            ownerAvatarUrl = repositoryModel.ownerAvatarUrl,
            ownerUrl = repositoryModel.ownerUrl,
            isFavorite = repositoryModel.isFavorite,
            stargazersCount = repositoryModel.stargazersCount,
            forksCount = repositoryModel.forksCount,
            createdAt = repositoryModel.createdAt
        )
    }


}
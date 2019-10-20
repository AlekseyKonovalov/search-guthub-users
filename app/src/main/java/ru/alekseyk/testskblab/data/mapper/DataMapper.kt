package ru.alekseyk.testskblab.data.mapper

import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity

internal object DataMapper {

    fun toRepositoryDBEntity(
        description: String?,
        fullName: String,
        id: Int,
        name: String,
        ownerId: Int,
        ownerLogin: String,
        ownerAvatarUrl: String,
        ownerUrl: String,
        isFavorite: Boolean,
        stargazersCount: Int,
        forksCount: Int,
        createdAt: String
    ): RepositoryDbEntity {
        return RepositoryDbEntity(
            description = description,
            fullName = fullName,
            id = id,
            name = name,
            ownerId = ownerId,
            ownerLogin = ownerLogin,
            ownerAvatarUrl = ownerAvatarUrl,
            ownerUrl = ownerUrl,
            isFavorite = isFavorite,
            stargazersCount = stargazersCount,
            forksCount = forksCount,
            createdAt = createdAt
        )

    }

}
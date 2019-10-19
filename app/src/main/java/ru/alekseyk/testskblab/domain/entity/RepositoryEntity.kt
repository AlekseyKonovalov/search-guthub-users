package ru.alekseyk.testskblab.domain.entity


data class RepositoryEntity(
    val description: String?,
    val fullName: String,
    val id: Int,
    val name: String,
    val ownerId: Int,
    val ownerLogin: String,
    val ownerAvatarUrl: String,
    val ownerUrl: String,
    val isFavorite: Boolean = false
)
package ru.alekseyk.testskblab.presentation.models

data class RepositoryModel(
    val description: String?,
    val fullName: String,
    val id: Int,
    val name: String,
    val ownerId: Int,
    val ownerLogin: String,
    val ownerAvatarUrl: String,
    val ownerUrl: String,
    val isStared: Boolean = false
)
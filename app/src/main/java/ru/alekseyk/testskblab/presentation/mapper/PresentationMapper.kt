package ru.alekseyk.testskblab.presentation.mapper

import ru.alekseyk.testskblab.domain.entity.UserEntity


internal object PresentationMapper {
    fun toUserEntity(accountName: String, isSignIn: Boolean = false): UserEntity {
        return UserEntity(accountName, isSignIn)
    }
}
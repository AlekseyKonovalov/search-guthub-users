package ru.alekseyk.testskblab.domain.entity

data class UserEntity (
    val accountEmail: String,
    val isSignIn: Boolean = false
)
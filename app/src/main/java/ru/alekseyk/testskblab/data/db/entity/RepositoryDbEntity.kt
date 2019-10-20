package ru.alekseyk.testskblab.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoryDbEntity(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val description: String?,
    @ColumnInfo
    val fullName: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val ownerId: Int,
    @ColumnInfo
    val ownerLogin: String,
    @ColumnInfo
    val ownerAvatarUrl: String,
    @ColumnInfo
    val ownerUrl: String,
    @ColumnInfo
    val isFavorite: Boolean = false,
    @ColumnInfo
    val stargazersCount: Int,
    @ColumnInfo
    val forksCount: Int,
    @ColumnInfo
    val createdAt: String,
    @ColumnInfo
    val accountEmail: String? = null
)
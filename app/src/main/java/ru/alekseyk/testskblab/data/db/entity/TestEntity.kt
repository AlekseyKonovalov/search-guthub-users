package ru.alekseyk.testskblab.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String
)
package ru.alekseyk.testskblab.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class RepositoryDbEntity(
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String
)
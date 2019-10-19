package ru.alekseyk.testskblab.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id", "accountEmail"], unique = true)])
data class UserDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int = 0,
    @ColumnInfo
    val accountEmail: String,
    @ColumnInfo
    val isSignIn: Boolean = false
)
package ru.alekseyk.testskblab.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alekseyk.testskblab.data.db.dao.RepositoryDao
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity

@Database(entities = [RepositoryDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}
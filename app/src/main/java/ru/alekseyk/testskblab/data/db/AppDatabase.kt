package ru.alekseyk.testskblab.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alekseyk.testskblab.data.db.dao.RepositoryDao
import ru.alekseyk.testskblab.data.db.dao.UserDao
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity

@Database(entities = [UserDbEntity::class, RepositoryDbEntity::class], version = 1)
/*@TypeConverters(DbConverters::class)*/
abstract class AppDatabase() : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao
}
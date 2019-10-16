package ru.alekseyk.testskblab.data.datasource.local


import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.AppDatabase

class LocalDataSource(private val appDatabase: AppDatabase) : IDataSource {

}
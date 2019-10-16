package ru.alekseyk.testskblab.repository

import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.domain.repository.IRepository

internal class Repository(
    private val localDataSource: IDataSource,
    private val remoteDataSource: IDataSource
)  : IRepository {

}
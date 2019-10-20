package ru.alekseyk.testskblab.data.datasource.local


import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.AppDatabase
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.data.dto.SearchRepositoriesListDto
import ru.alekseyk.testskblab.data.prefs.AppPrefs

class LocalDataSource(
    private val appDatabase: AppDatabase,
    private val appPrefs: AppPrefs
) : IDataSource {
    override fun deleteUserData(): Completable {
        return  Completable.fromCallable { appPrefs.removeCurrentUser() }
    }

    override fun getFavoritesRepositories(): Single<List<RepositoryDbEntity>> {
        return appDatabase.repositoryDao().getFavoriteRepositories()
            .map {
                it.filter { it.accountEmail == appPrefs.getCurrentUser() }
            }
    }

    override fun updateFavoriteStatus(repositoryEntity: RepositoryDbEntity): Completable {
        return appDatabase.repositoryDao()
            .insert(repositoryEntity.copy(accountEmail = appPrefs.getCurrentUser()))
    }

    override fun getRepositoriesBySearch(query: String): Observable<SearchRepositoriesListDto> {
        return Observable.error(Exception("Method only for RemoteDataSource realization"))
    }

    override fun getCurrentUserData(): Single<List<UserDbEntity>> {
        return appDatabase.userDao().getAll()
            .map {
                it.filter { it.accountEmail == appPrefs.getCurrentUser() }
            }

    }

    override fun setUserData(userDbEntity: UserDbEntity): Completable {
        appPrefs.saveCurrentUser(userDbEntity.accountEmail)
        return appDatabase.userDao().insert(userDbEntity)
    }

}
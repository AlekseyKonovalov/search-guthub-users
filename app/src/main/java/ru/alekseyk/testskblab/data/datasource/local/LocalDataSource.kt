package ru.alekseyk.testskblab.data.datasource.local


import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.AppDatabase
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.dto.SearchRepositoriesListDto
import ru.alekseyk.testskblab.data.prefs.AppPrefs
import ru.alekseyk.testskblab.domain.entity.RepositoryEntity

class LocalDataSource(
    private val appDatabase: AppDatabase,
    private val appPrefs: AppPrefs
) : IDataSource {
    override fun getFavoriteRepositoryById(repositoryId: Int): Single<RepositoryDbEntity?> {
        return appDatabase.repositoryDao().getFavoriteRepository(appPrefs.getCurrentUser() ?: "", repositoryId)
    }

    override fun addToFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable {
        return appDatabase.repositoryDao()
            .insert(repositoryEntity.copy(accountEmail = appPrefs.getCurrentUser() ?: ""))
    }

    override fun deleteFromFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable {
        return appDatabase.repositoryDao()
            .delete(repositoryEntity.copy(accountEmail = appPrefs.getCurrentUser() ?: ""))
    }

    override fun deleteUserData(): Completable {
        return Completable.fromCallable { appPrefs.removeCurrentUser() }
    }

    override fun getFavoritesRepositories(): Single<List<RepositoryDbEntity>> {
        return appDatabase.repositoryDao().getFavoriteRepositories(appPrefs.getCurrentUser() ?: "")
    }

    override fun getRepositoriesBySearch(query: String, pageSize: Int, position: Int): Observable<SearchRepositoriesListDto> {
        return Observable.error(Exception("Method only for RemoteDataSource realization"))
    }

    override fun getCurrentUserData(): Single<String> {
        return Single.just(appPrefs.getCurrentUser())

    }

    override fun setUserData(accountEmail: String): Completable {
        return Completable.fromCallable { appPrefs.saveCurrentUser(accountEmail) }

    }

}
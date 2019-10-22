package ru.alekseyk.testskblab.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.dto.SearchRepositoriesListDto
import ru.alekseyk.testskblab.data.mapper.DataMapper
import ru.alekseyk.testskblab.domain.repository.IRepository

internal class Repository(
    private val localDataSource: IDataSource,
    private val remoteDataSource: IDataSource
) : IRepository {
    override fun addToFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable {
        return  localDataSource.addToFavoritesRepositories(repositoryEntity)
    }

    override fun deleteFromFavoritesRepositories(repositoryEntity: RepositoryDbEntity): Completable {
        return  localDataSource.deleteFromFavoritesRepositories(repositoryEntity)
    }

    override fun deleteUserData(): Completable {
        return localDataSource.deleteUserData()
    }

    override fun getFavoritesRepositories(): Single<List<RepositoryDbEntity>> {
        return localDataSource.getFavoritesRepositories()
    }

    override fun getRepositoriesBySearch(query: String): Observable<List<RepositoryDbEntity>> {
        return Observable.zip( remoteDataSource.getRepositoriesBySearch(query),
            localDataSource.getFavoritesRepositories().toObservable(),
            BiFunction { remoteRepositories: SearchRepositoriesListDto,
                         favoriteRepositories:List<RepositoryDbEntity> ->
                return@BiFunction Pair(remoteRepositories, favoriteRepositories)
            })
            .flatMap {
                Observable.just(
                it.first.items?.map {remoteRepository->
                    DataMapper.toRepositoryDBEntity(
                        description = remoteRepository.description,
                        fullName = remoteRepository.fullName,
                        id = remoteRepository.id,
                        name = remoteRepository.name,
                        ownerId = remoteRepository.owner.id,
                        ownerLogin = remoteRepository.owner.login,
                        ownerAvatarUrl = remoteRepository.owner.avatarUrl,
                        ownerUrl = remoteRepository.owner.url,
                        isFavorite = it.second.any { remoteRepository.id == it.id },
                        stargazersCount = remoteRepository.stargazersCount,
                        forksCount = remoteRepository.forksCount,
                        createdAt = remoteRepository.createdAt
                    )
                }
                )
            }
    }

    override fun getCurrentUserData(): Single<String> {
        return localDataSource.getCurrentUserData()
    }

    override fun setUserData(accountEmail: String): Completable {
        return localDataSource.setUserData(accountEmail)
    }

}
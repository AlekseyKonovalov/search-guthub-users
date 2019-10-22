package ru.alekseyk.testskblab.domain.usecase

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.domain.entity.RepositoryEntity
import ru.alekseyk.testskblab.domain.mapper.DomainMapper
import ru.alekseyk.testskblab.domain.repository.IRepository

class RepositoriesUseCase(
    private val repository: IRepository
) {

    fun getRepositoriesBySearch(searchKey: String): Observable<List<RepositoryEntity>> {
        return repository.getRepositoriesBySearch(searchKey)
            .map {
                it.map {
                    DomainMapper.toRepositoryEntity(
                        description = it.description,
                        fullName = it.fullName,
                        id = it.id,
                        name = it.name,
                        ownerId = it.ownerId,
                        ownerLogin = it.ownerLogin,
                        ownerAvatarUrl = it.ownerAvatarUrl,
                        ownerUrl = it.ownerUrl,
                        isFavorite = it.isFavorite,
                        stargazersCount = it.stargazersCount,
                        forksCount = it.forksCount,
                        createdAt = it.createdAt
                    )
                }
            }
    }
    fun addToFavoritesRepositories(repositoryEntity: RepositoryEntity): Completable{
        return repository.addToFavoritesRepositories(DomainMapper.toRepositoryDbEntity(repositoryEntity))
    }
    fun deleteFromFavoritesRepositories(repositoryEntity: RepositoryEntity): Completable{
        return repository.deleteFromFavoritesRepositories(DomainMapper.toRepositoryDbEntity(repositoryEntity))
    }

/*    fun updateFavoriteStatus(repositoryEntity: RepositoryEntity): Completable {
        return repository.updateFavoriteStatus(DomainMapper.toRepositoryDbEntity(repositoryEntity))
    }*/

    fun getFavoritesRepositories(): Single<List<RepositoryEntity>> {
        return repository.getFavoritesRepositories()
            .map {
                it.map {
                    DomainMapper.toRepositoryEntity(
                        description = it.description,
                        fullName = it.fullName,
                        id = it.id,
                        name = it.name,
                        ownerId = it.ownerId,
                        ownerLogin = it.ownerLogin,
                        ownerAvatarUrl = it.ownerAvatarUrl,
                        ownerUrl = it.ownerUrl,
                        isFavorite = it.isFavorite,
                        stargazersCount = it.stargazersCount,
                        forksCount = it.forksCount,
                        createdAt = it.createdAt

                    )
                }
            }
    }


}
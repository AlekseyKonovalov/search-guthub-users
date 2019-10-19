package ru.alekseyk.testskblab.domain.usecase

import io.reactivex.Observable
import ru.alekseyk.testskblab.domain.entity.RepositoryEntity
import ru.alekseyk.testskblab.domain.mapper.DomainMapper
import ru.alekseyk.testskblab.domain.repository.IRepository

class RepositoriesUseCase(
    private val repository: IRepository
) {

    fun getRepositoriesBySearch(searchKey: String): Observable<List<RepositoryEntity>> {
        return repository.getRepositoriesBySearch(searchKey)
            .map {
                it.items?.map {
                    DomainMapper.toRepositoryEntity(
                        description = it.description,
                        fullName = it.fullName,
                        id = it.id,
                        name = it.name,
                        ownerId = it.owner.id,
                        ownerLogin = it.owner.login,
                        ownerAvatarUrl = it.owner.avatarUrl,
                        ownerUrl = it.owner.url
                    )
                }
            }
    }


}
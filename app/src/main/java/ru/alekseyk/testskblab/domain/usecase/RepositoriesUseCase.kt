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
            .map { it.map { DomainMapper.toRepositoryEntity(it) } }
    }


}
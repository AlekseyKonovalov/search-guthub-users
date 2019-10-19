package ru.alekseyk.testskblab.presentation.screen.repo_list.favorites_repo

import ru.alekseyk.testskblab.presentation.models.RepositoryModel

internal data class FavoritesRepoViewState(
    val payload: List<RepositoryModel> = emptyList(),
    val isLoading: Boolean = false
)

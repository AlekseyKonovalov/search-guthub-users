package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo

import ru.alekseyk.testskblab.presentation.models.RepositoryModel

data class SearchRepoViewState(
    val searchQuery: String = "",
    val payload: List<RepositoryModel> = emptyList(),
    val isLoading: Boolean = false
) {

    val isSearchMode: Boolean = searchQuery.isNotBlank()

}
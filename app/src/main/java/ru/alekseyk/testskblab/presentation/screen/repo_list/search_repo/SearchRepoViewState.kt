package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo

import androidx.paging.PagedList
import ru.alekseyk.testskblab.presentation.models.RepositoryModel
import ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.repo_list_adapter.PagingLoadingState

data class SearchRepoViewState(
    val searchQuery: String = "",
    val searchItems: PagedList<RepositoryModel>? = null,
    val isLoading: Boolean = false,
    val pagingLoadingState: PagingLoadingState = PagingLoadingState.DONE
) {

    val isSearchMode: Boolean = searchQuery.isNotBlank()

}
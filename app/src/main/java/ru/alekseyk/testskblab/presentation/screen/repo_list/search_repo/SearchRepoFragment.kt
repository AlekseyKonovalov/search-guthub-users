package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo

import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_search_repo.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateFragment
import ru.alekseyk.testskblab.presentation.ext.addTextChangedListener
import ru.alekseyk.testskblab.presentation.ext.diffedValue
import ru.alekseyk.testskblab.presentation.ext.hideKeyboard
import ru.alekseyk.testskblab.presentation.models.RepositoryModel
import ru.alekseyk.testskblab.presentation.screen.detail.DetailActivity
import ru.alekseyk.testskblab.presentation.screen.repo_list.repo_list_adapter.RepositoriesAdapter

class SearchRepoFragment : StateFragment<SearchRepoViewState>(
    layoutResource = R.layout.fragment_search_repo
) {

    override val viewModel by viewModel<SearchRepoViewModel>()

    private val adapter by lazy {
        RepositoriesAdapter(
            onItemClick = ::onRepositoryClick,
            onActionClick = viewModel::updateFavoriteStatus
        )
    }

    override fun initViews() {
        repo_list_parcels_rv.adapter = adapter
    }

    override fun initListeners() {
        search_key_edt.addTextChangedListener {
            viewModel.updateSearchQuery(it)
        }
        repolist_search_clear_btn.setOnClickListener { search_key_edt.text.clear() }
        repolist_search_btn.setOnClickListener { updateSearch() }

    }

    override fun render(state: SearchRepoViewState) {
        val isItemsListEmpty = state.payload.isEmpty()
        val isSearchMode = state.isSearchMode

        repo_list_parcels_rv.isVisible = !isItemsListEmpty
        search_placeholder_layout.isVisible = isItemsListEmpty && !isSearchMode

        repolist_search_clear_btn.isVisible = isSearchMode
        general_progressbar.isVisible = state.isLoading && isSearchMode

        search_key_edt.diffedValue = state.searchQuery

        adapter.items = state.payload

        if (!isSearchMode) {
            activity?.hideKeyboard()
            search_key_edt.clearFocus()
        }
    }


    private fun onRepositoryClick(repositoryModel: RepositoryModel) {
        activity?.let { DetailActivity.startActivity(it, repositoryModel) }
    }

    fun updateSearch() {
        viewModel.requestData()
    }

}
package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo

import android.view.KeyEvent
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_search_repo.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateFragment
import ru.alekseyk.testskblab.presentation.ext.diffedValue
import ru.alekseyk.testskblab.presentation.ext.hideKeyboard
import ru.alekseyk.testskblab.presentation.models.RepositoryModel
import ru.alekseyk.testskblab.presentation.screen.detail.DetailActivity
import ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.repo_list_adapter.RepositoriesAdapter

class SearchRepoFragment : StateFragment<SearchRepoViewState>(
    layoutResource = R.layout.fragment_search_repo
) {

    override val viewModel by viewModel<SearchRepoViewModel>()

    private val adapter by lazy {
        RepositoriesAdapter(
            onItemClick = ::onRepositoryClick,
            onRetryClickListener = viewModel::onPagingRetryBtnClickListener
        )
    }

    override fun initViews() {
        repo_list_parcels_rv.adapter = adapter
    }

    override fun initListeners() {
        search_key_edt.setOnKeyListener { _, i, keyEvent ->
            if ((keyEvent.action == KeyEvent.ACTION_DOWN) &&
                (i == KeyEvent.KEYCODE_ENTER)
            ) {
                viewModel.updateSearchQuery(search_key_edt.text.toString())
                true
            }
            false
        }
        repolist_search_btn.setOnClickListener {
            viewModel.updateSearchQuery(search_key_edt.text.toString())
        }
        repolist_search_clear_btn.setOnClickListener { search_key_edt.text.clear() }

    }

    override fun render(state: SearchRepoViewState) {
        search_key_edt.diffedValue = state.searchQuery
        search_placeholder_layout.isVisible = state.searchItems.isNullOrEmpty() && !state.isSearchMode

        adapter.setLoadingState(state.pagingLoadingState)
        state.searchItems?.let {
            adapter.submitList(it)
        }

        if (!state.isSearchMode) {
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
package ru.alekseyk.testskblab.presentation.screen.repo_list.favorites_repo

import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_favorites_repo.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateFragment
import ru.alekseyk.testskblab.presentation.models.RepositoryModel
import ru.alekseyk.testskblab.presentation.screen.detail.DetailActivity
import ru.alekseyk.testskblab.presentation.screen.repo_list.repo_list_adapter.RepositoriesAdapter

class FavoritesRepoFragment : StateFragment<FavoritesRepoViewState>(
    layoutResource = R.layout.fragment_favorites_repo
) {
    private val adapter by lazy {
        RepositoriesAdapter(
            onItemClick = ::onRepositoryClick,
            onActionClick = viewModel::updateFavoriteStatus
        )
    }

    override val viewModel by viewModel<FavoritesRepoViewModel>()

    override fun onResume() {
        super.onResume()
        viewModel.getFavoritesRepositories()
    }

    override fun initViews() {
        repo_list_parcels_rv.adapter = adapter
    }

    override fun initListeners() {}


    override fun render(state: FavoritesRepoViewState) {
        general_progressbar.isVisible = state.isLoading
        search_placeholder_layout.isVisible = state.payload.isEmpty() && !state.isLoading
        adapter.items = state.payload
    }

    private fun onRepositoryClick(repositoryModel: RepositoryModel) {
        activity?.let { DetailActivity.startActivity(it, repositoryModel) }
    }

    fun updateFavoritesList() {
        viewModel.getFavoritesRepositories()
    }
}
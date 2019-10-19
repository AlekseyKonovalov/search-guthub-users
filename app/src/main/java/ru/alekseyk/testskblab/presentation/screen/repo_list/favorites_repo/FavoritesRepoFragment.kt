package ru.alekseyk.testskblab.presentation.screen.repo_list.favorites_repo

import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_search_repo.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateFragment

internal class FavoritesRepoFragment : StateFragment<FavoritesRepoViewState>(
    layoutResource = R.layout.fragment_favorites_repo
) {

    override val viewModel by viewModel<FavoritesRepoViewModel> ()

    override fun initViews() {

    }


    override fun render(state: FavoritesRepoViewState) {
        general_progressbar.isVisible = state.isLoading
    }

}
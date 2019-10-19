package ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo

import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_search_repo.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateFragment

internal class SearchRepoFragment : StateFragment<SearchRepoViewState>(
    layoutResource = R.layout.fragment_search_repo
) {

    override val viewModel by viewModel<SearchRepoViewModel> ()

    override fun initViews() {

    }


    override fun render(state: SearchRepoViewState) {
        general_progressbar.isVisible = state.isLoading
    }

}
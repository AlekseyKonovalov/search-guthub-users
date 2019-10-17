package ru.alekseyk.testskblab.presentation.screen.repo_list

import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_repolist.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateActivity

internal class RepoListActivity : StateActivity<RepoListViewState>(
    layoutResource = R.layout.activity_repolist
) {

    override val viewModel by viewModel<RepoListViewModel>()

    override fun render(state: RepoListViewState) {
        general_progressbar.isVisible = state.isLoading
    }

    override fun initViews() {
        general_toolbar.title = "Main Activity"
    }
}

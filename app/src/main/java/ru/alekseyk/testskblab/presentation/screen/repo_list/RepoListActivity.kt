package ru.alekseyk.testskblab.presentation.screen.repo_list

import android.content.Context
import android.content.Intent
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateActivity

internal class RepoListActivity : StateActivity<RepoListViewState>(
    layoutResource = R.layout.activity_repolist
) {

    override val viewModel by viewModel<RepoListViewModel>()

    override fun render(state: RepoListViewState) {
    }

    override fun initViews() {
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RepoListActivity::class.java))
        }
    }

}

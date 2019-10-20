package ru.alekseyk.testskblab.presentation.screen.repo_list

import android.animation.LayoutTransition
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_repolist.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateActivity
import ru.alekseyk.testskblab.presentation.ext.buildAlertDialog
import ru.alekseyk.testskblab.presentation.screen.auth.AuthActivity
import ru.alekseyk.testskblab.presentation.screen.repo_list.favorites_repo.FavoritesRepoFragment
import ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.SearchRepoFragment

internal class RepoListActivity : StateActivity<RepoListViewState>(
    layoutResource = R.layout.activity_repolist
) {

    override val viewModel by viewModel<RepoListViewModel>()

    override fun render(state: RepoListViewState) {
        general_progressbar.isVisible = state.isLoading
        if (state.isFinish) {
            AuthActivity.startActivity(this)
            finish()
        }
    }

    override fun initViews() {
        general_toolbar.title = "Github repositories"
        setSupportActionBar(general_toolbar)


        (container as ViewGroup).layoutTransition.apply {
            enableTransitionType(LayoutTransition.APPEARING)
            enableTransitionType(LayoutTransition.DISAPPEARING)
        }
        view_pager.adapter = RepoListPagerAdapter(supportFragmentManager, this@RepoListActivity)
        tab_layout.setupWithViewPager(view_pager)
        tab_layout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(currentTab: TabLayout.Tab) {
                view_pager.currentItem = currentTab.position
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_update -> {
                (supportFragmentManager.fragments.firstOrNull() as SearchRepoFragment)?.let { it.updateSearch() }
                (supportFragmentManager.fragments[1] as FavoritesRepoFragment)?.let { it.updateFavoritesList() }
                true
            }
            R.id.action_logout -> {
                this.buildAlertDialog(
                    title = "Выйти из аккаунта",
                    onPositiveBtnClick = { viewModel.deleteUserData() }).show()
                true
            }
            else -> true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RepoListActivity::class.java))
        }
    }

}

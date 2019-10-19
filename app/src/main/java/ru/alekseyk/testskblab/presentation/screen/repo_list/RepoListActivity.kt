package ru.alekseyk.testskblab.presentation.screen.repo_list

import android.animation.LayoutTransition
import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_repolist.*
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

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RepoListActivity::class.java))
        }
    }

}

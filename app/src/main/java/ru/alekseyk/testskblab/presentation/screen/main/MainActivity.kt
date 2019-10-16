package ru.alekseyk.testskblab.presentation.screen.main

import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateActivity

internal class MainActivity : StateActivity<MainViewState>(
    layoutResource = R.layout.activity_main
) {

    override val viewModel by viewModel<MainViewModel>()

    override fun render(state: MainViewState) {
        general_progressbar.isVisible = state.isLoading
    }

    override fun initViews() {
        general_toolbar.title = "Main Activity"
    }
}

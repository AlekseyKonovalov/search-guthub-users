package ru.alekseyk.testskblab.presentation.screen.detail

import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateActivity

internal class DetailActivity : StateActivity<DetailViewState>(
    layoutResource = R.layout.activity_detail
) {

    override val viewModel by viewModel<DetailViewModel>()

    override fun render(state: DetailViewState) {
        general_progressbar.isVisible = state.isLoading
    }


    override fun initViews() {
        general_toolbar.title = "Detail Activity"
    }
}

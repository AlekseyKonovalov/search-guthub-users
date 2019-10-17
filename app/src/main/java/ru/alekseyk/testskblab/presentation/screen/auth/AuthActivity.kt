package ru.alekseyk.testskblab.presentation.screen.auth

import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_repolist.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateActivity

internal class AuthActivity : StateActivity<AuthViewState>(
    layoutResource = R.layout.activity_auth
) {

    override val viewModel by viewModel<AuthViewModel>()

    override fun render(state: AuthViewState) {
        general_progressbar.isVisible = state.isLoading
    }

    override fun initViews() {

    }
}

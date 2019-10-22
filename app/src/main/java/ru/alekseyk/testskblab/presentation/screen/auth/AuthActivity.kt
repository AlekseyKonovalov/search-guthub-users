package ru.alekseyk.testskblab.presentation.screen.auth

import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.gms.common.AccountPicker
import kotlinx.android.synthetic.main.activity_auth.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateActivity
import ru.alekseyk.testskblab.presentation.screen.repo_list.RepoListActivity


class AuthActivity : StateActivity<AuthViewState>(
    layoutResource = R.layout.activity_auth
) {

    override val viewModel by viewModel<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCurrentUserData()
    }

    override fun render(state: AuthViewState) {
        general_progressbar.isVisible = state.isLoading

        if (state.isFinish) {
            RepoListActivity.startActivity(this@AuthActivity,
                state.accountName.split("@").first())
            finish()
        }
    }

    override fun initViews() {}

    override fun initListeners() {
        sign_in_button.setOnClickListener {
            val intent = AccountPicker.newChooseAccountIntent(
                null, null, arrayOf("com.google"),
                false, null, null, null, null
            )
            startActivityForResult(intent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            data?.let {
                it.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)?.let {
                    viewModel.setUserData(it)
                }
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, AuthActivity::class.java))
        }
    }


}

package ru.alekseyk.testskblab.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_base.*

internal abstract class BaseFragment(
    @LayoutRes private val layoutResource: Int
) : Fragment() {

    protected open val appBarView: AppBarLayout? by lazy { general_appbar }
    protected open val contentContainerView: ViewGroup? by lazy { general_content_layout }
    protected open val toolbarView: Toolbar? by lazy { general_toolbar }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResource, null)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        initViewModelObserving()
    }

    protected abstract fun initViewModelObserving()
    protected abstract fun initViews()
    protected abstract fun initListeners()

    protected open fun showLoading(isShow: Boolean) {}
    protected open fun showError(errorMessage: String) {}
    protected open fun showPlaceholder(isShow: Boolean, titleText: String? = null) {}

}
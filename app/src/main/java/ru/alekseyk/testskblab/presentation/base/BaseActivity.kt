package ru.alekseyk.testskblab.presentation.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_base.*
import ru.alekseyk.testskblab.presentation.ext.setNavigationOnClickListener

internal abstract class BaseActivity(
    @LayoutRes private val layoutResource: Int
) : AppCompatActivity() {

    protected open val appBarView: AppBarLayout? by lazy { general_appbar }
    protected open val contentContainerView: ViewGroup? by lazy { general_content_layout }
    protected open val toolbarView: Toolbar? by lazy { general_toolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initViews()
        initListeners()
        initViewModelObserving()
    }

    @CallSuper
    protected open fun initListeners() {
        toolbarView?.setNavigationOnClickListener(::finish)
    }

    protected abstract fun initViews()
    protected abstract fun initViewModelObserving()


}
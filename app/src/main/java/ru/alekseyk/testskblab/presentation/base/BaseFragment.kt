package ru.alekseyk.testskblab.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import ru.alekseyk.testskblab.presentation.ext.inflate

abstract class BaseFragment(
    @LayoutRes private val layoutResource: Int
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflate(layoutResource)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        initListeners()
        initViewModelObserving()
    }

    protected abstract fun initViewModelObserving()
    protected abstract fun initViews()
    protected abstract fun initListeners()

}
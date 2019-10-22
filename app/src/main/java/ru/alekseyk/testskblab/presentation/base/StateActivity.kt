package ru.alekseyk.testskblab.presentation.base

import androidx.annotation.LayoutRes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

abstract class StateActivity<ViewState : Any>(
    @LayoutRes layoutResource: Int
) : BaseActivity(layoutResource) {

    protected abstract val viewModel: StateViewModel<ViewState>

    protected val currentState: ViewState
        get() = viewModel.currentState

    private val disposable = CompositeDisposable()

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    override fun initViewModelObserving() {
        lifecycle.addObserver(viewModel)
        viewModel.state
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = ::render)
            .addTo(disposable)
    }

    protected abstract fun render(state: ViewState)

}
package ru.alekseyk.testskblab.presentation.base

import android.widget.TextView
import androidx.annotation.LayoutRes
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.presentation.ext.distinctByValue
import java.util.concurrent.TimeUnit

internal abstract class StateFragment<ViewState : Any>(
    @LayoutRes layoutResource: Int
) : BaseFragment(layoutResource) {

    protected abstract val viewModel: StateViewModel<ViewState>

    protected val currentState: ViewState
        get() = viewModel.currentState

    protected val viewDisposable = CompositeDisposable()

    private val disposable = CompositeDisposable()

    override fun onStop() {
        super.onStop()
        disposable.clear()
        viewDisposable.clear()
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

    protected fun TextView.listenChanges(
        stateDifferentiator: () -> String,
        resultApplier: (String) -> Unit,
        debounce: Boolean = false
    ) {
        var textChangedListener: Observable<CharSequence> = textChanges()

        if (debounce) textChangedListener = textChangedListener.debounce(
            500, TimeUnit.MILLISECONDS
        )

        textChangedListener
            .map(CharSequence::toString)
            .distinctByValue(stateDifferentiator::invoke)
            .subscribeBy(onNext = resultApplier::invoke)
            .addTo(viewDisposable)
    }

}
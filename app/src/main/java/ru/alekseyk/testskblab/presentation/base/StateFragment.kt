package ru.alekseyk.testskblab.presentation.base

import android.widget.EditText
import androidx.annotation.LayoutRes
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.presentation.ext.addTextChangedListener
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

    protected fun EditText.listenChanges(
        stateDifferentiator: () -> String,
        resultApplier: (String) -> Unit
    ) {
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            this.addTextChangedListener {
                subscriber.onNext(it)
            }
        })
            .map { text -> text.toLowerCase().trim() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinct()
            .filter { text -> text.isNotBlank() }
            .distinctByValue(stateDifferentiator::invoke)
            .subscribeBy(onNext = resultApplier::invoke)
            .addTo(viewDisposable)

    }

}
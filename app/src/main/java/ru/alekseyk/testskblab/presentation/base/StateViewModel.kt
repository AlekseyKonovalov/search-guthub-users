package ru.alekseyk.testskblab.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

abstract class StateViewModel<ViewState : Any>(defaultState: ViewState) : ViewModel(), LifecycleObserver {

    val state: Observable<ViewState>
        get() = _state

    var currentState: ViewState = defaultState
        private set

    protected val disposables = CompositeDisposable()

    private val _state: Subject<ViewState> = BehaviorSubject.create<ViewState>()
        .apply { onNext(currentState) }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


    @Synchronized
    protected fun updateState(state: ViewState, noRender: Boolean = false) {
        currentState = state
        if (noRender.not()) _state.onNext(currentState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun dispose() {
        disposables.clear()
    }

}
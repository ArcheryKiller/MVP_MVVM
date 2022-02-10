package pavel.ivanov.mvp_mvvm.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

private val compositeDisposable = CompositeDisposable()

fun observable() = Observable.just(1)

fun execObservable() {
    compositeDisposable += observable().subscribe()
}

operator fun CompositeDisposable.plusAssign(d: Disposable) {
    add(d)
}

fun onDestroy() {
    compositeDisposable.dispose()
}
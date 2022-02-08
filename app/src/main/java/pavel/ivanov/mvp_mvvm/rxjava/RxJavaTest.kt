package pavel.ivanov.mvp_mvvm.rxjava

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class RxJavaTest {

// ===========================================================
// Observable.create
// ===========================================================
    fun observableCreate() {
        val observ = Observable.create<String> { emitter ->
            try {
                emitter.onNext("New Value")
            } catch (e: Throwable) {
                emitter.onError(IllegalArgumentException("Error: $emitter"))
            }
            emitter.onComplete()
        }
        observ.subscribe({
            println("Событие = $it")
        }, { error
            ->
            println(error.message)
        }, {
                println("Поток завершился")
            }
        )
    Thread.sleep(2000)
    }

// ===========================================================
// Observable.just
// ===========================================================
    fun observableJust() {
        val observ = Observable
            .just(1, 2, 1,3, 2, 2)
            .map {event -> UserInput.values().find { it.id ==event } ?: UserInput.UPDATE_SCREEN }
            .distinctUntilChanged()

        observ.subscribe({
            println("Событие = $it")
        }, { error
            ->
            println(error.message)
        }, {
            println("Поток завершился")
        }
        )
        Thread.sleep(2000)
    }

// ===========================================================
// Observable.fromIterable
// ===========================================================
    fun observableFromIterable() {
        val observ = Observable.fromIterable(listOf("Test item 1", "Test item 2", "Test item 2"))

        observ.subscribe({
            println("Событие = $it")
        }, { error
            ->
            println(error.message)
        }, {
            println("Поток завершился")
        }
        )
        Thread.sleep(2000)
    }

// ===========================================================
// Observable.interval
// ===========================================================
    fun observableInterval() {
        val observ = Observable.interval(1, TimeUnit.SECONDS)
            .map { "map from $it" }

        val sub = observ.subscribe({
                println("Событие = $it")
            }, { error
                ->
                println(error.message)
            }, {
                println("Поток завершился")
            }
            )
        Thread.sleep(3000)
        sub.dispose()
        Thread.sleep(10000)
    }

// ===========================================================
// Observable.timer
// ===========================================================
    fun observableTimer() {
        val observ = Observable
            .timer(2, TimeUnit.SECONDS)
            .map { "map from $it" }
            .doOnSubscribe { println("На нашу цепочку кто-то подписался") }

        val sub = observ.subscribe({
            println("Событие = $it")
        }, { error
            ->
            println(error.message)
        }, {
            println("Поток завершился")
        }
        )
        Thread.sleep(3000)
        sub.dispose()
        Thread.sleep(10000)
    }

// ===========================================================
// Observable.range
// ===========================================================
    fun observableRange() {
        val observ = Observable
            .range(2, 132)
            .skip(5)
            .take(40)
            .map { "map from $it" }
            .doOnSubscribe { println("На нашу цепочку кто-то подписался") }

        val sub = observ.subscribe({
            println("Событие = $it")
        }, { error
            ->
            println(error.message)
        }, {
            println("Поток завершился")
        }
        )
        Thread.sleep(3000)
        sub.dispose()
        Thread.sleep(10000)
    }

// ===========================================================
// Observable.fromCallable
// ===========================================================
    fun observableFromCallable() {
        val observ = Observable
            .fromCallable {
                println("fromCallable start")
                Thread.sleep(2000)
                println("fromCallable stop")
                return@fromCallable 900
            }
            .map { "map from $it" }
            .doOnSubscribe { println("На нашу цепочку кто-то подписался") }

        val sub = observ.subscribe({
            println("Событие = $it")
        }, { error
            ->
            println(error.message)
        }, {
            println("Поток завершился")
        }
        )
        Thread.sleep(5000)
    }
}

enum class UserInput(val id: Int) {
    SEND_REQUEST(1),
    UPDATE_SCREEN(2),
    PAY_FOR_SOMETHING(1)
}
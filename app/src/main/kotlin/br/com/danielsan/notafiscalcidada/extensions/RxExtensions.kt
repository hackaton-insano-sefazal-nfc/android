package br.com.danielsan.notafiscalcidada.extensions

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

/**
 * Created by daniel on 18/08/17.
 */

fun <T> Single<T>.observeOnMainUi(): Single<T> {
    return observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.subscribeOnIo(): Single<T> {
    return subscribeOn(Schedulers.io())
}

fun <T> Maybe<T>.observeOnMainUi(): Maybe<T> {
    return observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.subscribeOnIo(): Maybe<T> {
    return subscribeOn(Schedulers.io())
}

fun <T> Observable<T>.observeOnMainUi(): Observable<T> {
    return observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.subscribeOnIo(): Observable<T> {
    return subscribeOn(Schedulers.io())
}

fun <T> Flowable<T>.observeOnMainUi(): Flowable<T> {
    return observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.subscribeOnIo(): Flowable<T> {
    return subscribeOn(Schedulers.io())
}

inline fun <T> Single<T>.socketTimeoutRetry(): Single<T> = this.retryWhen { errors ->
    errors.doOnNext { Timber.e(it, "Start retry") }
            .flatMap { if (it is SocketTimeoutException) Flowable.just(it) else Flowable.error(it) }
            .zipWith(Flowable.range(1, 3), BiFunction<Throwable, Int, Int> { _, i -> i })
            .concatMap { retryCount -> Flowable.timer(Math.pow(2.toDouble(), retryCount.toDouble()).toLong(), TimeUnit.SECONDS) }
}

package com.geekbrains.librariespopular.utils

import android.os.Handler

private data class Subscriber<T>(
    private val handler: Handler,
    private val callback: (T?) -> Unit
) {
    //Метод invoke(Оператор вызова)
    fun invoke(value: T?) {
        handler.post {
            callback.invoke(value)
        }
    }
}

class Publisher<T>(private val isSingle: Boolean = false) {
    private val subscribers: MutableSet<Subscriber<T?>> = mutableSetOf()
    var value: T? = null
        private set
    private var hasFirstValue = false

    // подписываемся
    fun subscribe(handler: Handler, callback: (T?) -> Unit) {
        val subscriber = Subscriber(handler, callback)
        subscribers.add(subscriber)
        if (hasFirstValue) {
            subscriber.invoke(value)
        }
    }

    // отписываемся от всего
    fun unsubscribeAll() {
        subscribers.clear()
    }

    //отправляем значения
    fun post(value: T) {
        if (!isSingle) {
            hasFirstValue = true
            this.value = value
        }
        subscribers.forEach {
            it.invoke(value)
        }
    }
}
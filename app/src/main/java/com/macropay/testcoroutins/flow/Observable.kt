package com.macropay.testcoroutins.flow

//Un observer generico,
class Observable<T> (var value: T){
    private val observers:MutableList<(T)->Unit> = mutableListOf()
    fun subscribe(observer:(T)->Unit){
        observers.add(observer)
        observer(value)
    }
    fun unsubscribe(observer:(T)->Unit){
        observers.remove (observer)
    }
    fun updateValue(newValue:T){
        value = newValue
        notifyObservers()

    }

    private fun notifyObservers() {
         observers.forEach{it(value)}
    }

}
package dev.andrewbailey.incrementer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _counterValue = MutableLiveData<Int>()
    val counterValue: LiveData<Int> = _counterValue

    init {
        _counterValue.value = 0
    }

    fun incrementCounter() {
        _counterValue.value = requireCounterValue() + 1
    }

    fun decrementCounter() {
        _counterValue.value = requireCounterValue() - 1
    }

    private fun requireCounterValue(): Int {
        return requireNotNull(_counterValue.value) {
            "CounterValue live data did not contain a value. Did you null it out?"
        }
    }

}

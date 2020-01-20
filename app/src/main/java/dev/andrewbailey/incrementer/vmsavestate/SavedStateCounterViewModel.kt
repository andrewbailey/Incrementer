package dev.andrewbailey.incrementer.vmsavestate

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateCounterViewModel(
    private val state: SavedStateHandle
) : ViewModel() {

    private val _counterValue = state.getLiveData(KEY_COUNTER_VALUE, 0)
    val counterValue: LiveData<Int> = _counterValue

    fun incrementCounter() {
        val updatedValue = requireCounterValue() + 1
        state.set(KEY_COUNTER_VALUE, updatedValue)
        _counterValue.value =  updatedValue
    }

    fun decrementCounter() {
        val updatedValue = requireCounterValue() - 1
        state.set(KEY_COUNTER_VALUE, updatedValue)
        _counterValue.value =  updatedValue
    }

    private fun requireCounterValue(): Int {
        return requireNotNull(_counterValue.value) {
            "CounterValue live data did not contain a value. Was it reset?"
        }
    }

    companion object {
        private const val KEY_COUNTER_VALUE = "CounterViewModel.Counter"
    }

}

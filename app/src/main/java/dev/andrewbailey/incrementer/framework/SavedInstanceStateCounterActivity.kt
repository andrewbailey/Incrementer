package dev.andrewbailey.incrementer.framework

import android.os.Bundle
import dev.andrewbailey.incrementer.CounterActivity

class SavedInstanceStateCounterActivity : CounterActivity() {

    companion object {
        private const val EXTRA_SAVED_COUNT = "CounterActivity.Counter"
    }

    private var counter = 0
        set(value) {
            field = value
            setCounterValue(value)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        counter = savedInstanceState?.getInt(EXTRA_SAVED_COUNT, 0) ?: 0
        setCounterValue(counter)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_SAVED_COUNT, counter)
    }

    override fun onIncrementCounter() {
        counter++
    }

    override fun onDecrementCounter() {
        counter--
    }

}

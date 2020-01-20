package dev.andrewbailey.incrementer.configchanges

import android.os.Bundle
import dev.andrewbailey.incrementer.CounterActivity

class ConfigChangesCounterActivity : CounterActivity() {

    private var counter = 0
        set(value) {
            field = value
            setCounterValue(value)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCounterValue(counter)
    }

    override fun onIncrementCounter() {
        counter++
    }

    override fun onDecrementCounter() {
        counter--
    }

}

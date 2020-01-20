package dev.andrewbailey.incrementer.notresizable

import android.os.Bundle
import dev.andrewbailey.incrementer.CounterActivity

class PortraitLockedNotResizableCounterActivity : CounterActivity() {

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

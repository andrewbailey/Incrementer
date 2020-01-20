package dev.andrewbailey.incrementer.vmsavestate

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import dev.andrewbailey.incrementer.CounterActivity

class VMSavedStateCounterActivity : CounterActivity() {

    private lateinit var viewModel: SavedStateCounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, SavedStateViewModelFactory(application, this))
            .get(SavedStateCounterViewModel::class.java)

        viewModel.counterValue.observe(this, Observer { count ->
            setCounterValue(count)
        })
    }

    override fun onIncrementCounter() {
        viewModel.incrementCounter()
    }

    override fun onDecrementCounter() {
        viewModel.decrementCounter()
    }

}

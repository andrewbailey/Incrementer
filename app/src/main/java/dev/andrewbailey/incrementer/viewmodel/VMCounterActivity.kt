package dev.andrewbailey.incrementer.viewmodel

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.andrewbailey.incrementer.CounterActivity

class VMCounterActivity : CounterActivity() {

    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.NewInstanceFactory().create(CounterViewModel::class.java)

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

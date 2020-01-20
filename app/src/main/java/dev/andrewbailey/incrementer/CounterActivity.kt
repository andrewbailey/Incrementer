package dev.andrewbailey.incrementer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.andrewbailey.incrementer.databinding.ActivityCounterBinding

abstract class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.incrementButton.setOnClickListener {
            onIncrementCounter()
        }

        binding.decrementButton.setOnClickListener {
            onDecrementCounter()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun setCounterValue(value: Int) {
        binding.counterValue.text = value.toString()
    }

    abstract fun onIncrementCounter()

    abstract fun onDecrementCounter()

}

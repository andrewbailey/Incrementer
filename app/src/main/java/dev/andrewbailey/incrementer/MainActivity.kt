package dev.andrewbailey.incrementer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.andrewbailey.incrementer.databinding.ActivityMainBinding
import dev.andrewbailey.incrementer.framework.SavedInstanceStateCounterActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.implementationsList.apply {
            implSavedInstanceStateButton.setOnClickListener {
                launchCounterActivity<SavedInstanceStateCounterActivity>()
            }
        }
    }

    private inline fun <reified T : CounterActivity> launchCounterActivity() {
        startActivity(Intent(this, T::class.java))
    }
}

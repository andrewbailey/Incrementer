package dev.andrewbailey.incrementer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.andrewbailey.incrementer.configchanges.ConfigChangesCounterActivity
import dev.andrewbailey.incrementer.databinding.ActivityMainBinding
import dev.andrewbailey.incrementer.framework.SavedInstanceStateCounterActivity
import dev.andrewbailey.incrementer.notresizable.PortraitLockedNotResizableCounterActivity
import dev.andrewbailey.incrementer.portrait.PortraitLockedCounterActivity
import dev.andrewbailey.incrementer.viewmodel.VMCounterActivity
import dev.andrewbailey.incrementer.vmsavestate.VMSavedStateCounterActivity

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
            implViewModelWithSaveStateButton.setOnClickListener {
                launchCounterActivity<VMSavedStateCounterActivity>()
            }
            implPortraitLockedButton.setOnClickListener {
                launchCounterActivity<PortraitLockedCounterActivity>()
            }
            implPortraitNotResizableButton.setOnClickListener {
                launchCounterActivity<PortraitLockedNotResizableCounterActivity>()
            }
            implConfigChangesButton.setOnClickListener {
                launchCounterActivity<ConfigChangesCounterActivity>()
            }
            implViewModelOnlyButton.setOnClickListener {
                launchCounterActivity<VMCounterActivity>()
            }
        }
    }

    private inline fun <reified T : CounterActivity> launchCounterActivity() {
        startActivity(Intent(this, T::class.java))
    }
}

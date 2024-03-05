package com.example.healthconnectsample.presentation.steps


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.lifecycle.lifecycleScope
import com.example.healthconnectsample.presentation.BaseApplication
import kotlinx.coroutines.launch

class StepsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val healthConnectManager = (application as BaseApplication).healthConnectManager

        super.onCreate(savedInstanceState)
        setContent {
            Text("Steps activity")
        }

        lifecycleScope.launch {
            Log.d(StepsActivity::class.simpleName, "sync started")
            healthConnectManager.resyncSteps()
            Log.d(StepsActivity::class.simpleName, "sync finished")
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
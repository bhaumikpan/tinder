package com.example.tinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.feature_ui.view.FeatureActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(R.layout.main_activity)

        findViewById<Button>(R.id.next).setOnClickListener {
            startActivity(Intent(this@MainActivity, FeatureActivity::class.java))
        }
    }
}
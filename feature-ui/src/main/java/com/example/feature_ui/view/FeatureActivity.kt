package com.example.feature_ui.view

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.feature_ui.R

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feature_activity)

        // Check if the fragment container is empty
        if (savedInstanceState == null) {
            // Create a new fragment instance
            val fragment = ListFragment()

            // Add the fragment to the container
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
package com.example.multitrantest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multitrantest.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextFrag = MainFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, nextFrag)
            .commit()

        supportActionBar?.hide()

    }
}
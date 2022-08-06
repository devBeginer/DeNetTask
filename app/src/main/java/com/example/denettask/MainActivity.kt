package com.example.denettask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private val viewModel:TreeViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onStop() {
        viewModel.save()
        super.onStop()
    }
}
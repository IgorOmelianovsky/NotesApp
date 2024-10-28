package com.example.p71_notes_real.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.p71_notes_real.R
import com.example.p71_notes_real.repository.Repository
import com.example.p71_notes_real.viewmodel.MainViewModel
import com.example.p71_notes_real.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1200)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        initialiseViewModel()
    }

    private fun initialiseViewModel() {
        val repository = Repository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

}
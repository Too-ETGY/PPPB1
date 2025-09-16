package com.example.d4_myshop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.d4_myshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    bikin variable binding
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        assign value binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        interaksi dengan binding
        with(binding){
            val navController = findNavController(R.id.nav_host_fragment)
            bottomNavigationView.setupWithNavController(navController)
        }
    }
}
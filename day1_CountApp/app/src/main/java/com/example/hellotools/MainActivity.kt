package com.example.hellotools

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hellotools.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((binding.root))

        with(receiver = binding){
            txtNumber.text = number.toString()

            btnCount.setOnClickListener {
                number = number+1
                txtNumber.text = number.toString()
            }

            btnToast.setOnClickListener {
                Toast.makeText(this@MainActivity,
                    "Toast $number",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}
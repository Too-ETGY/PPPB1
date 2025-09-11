package com.example.d3_intentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.d3_intentapp.databinding.ActivityMainBinding
import com.example.d3_intentapp.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_third)

        with(binding){
            val resultIntent = Intent()

            resultIntent.putExtra("address", edtAddress.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }
    }
}
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
        setContentView(binding.root)

        // Ambil data dari Intent
        val name = intent.getStringExtra("name") ?: "-"
        val nim = intent.getStringExtra("nim") ?: "-"
        val prodi = intent.getStringExtra("prodi") ?: "-"
        val umur = intent.getStringExtra("umur") ?: "-"
        val hobby = intent.getStringExtra("hobby") ?: "-"

        // Tampilkan ke TextView
        with(binding) {
            namaDtl.text = name
            nimDtl.text = nim
            prodiDtl.text = prodi
            umurDtl.text = umur
            hobbyDtl.text = hobby

            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}
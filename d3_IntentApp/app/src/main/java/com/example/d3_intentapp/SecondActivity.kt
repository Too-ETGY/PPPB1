package com.example.d3_intentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.d3_intentapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        with(binding){

            btnBack.setOnClickListener {
                finish()
            }

            btnSimpan.setOnClickListener {
                val intentToMainActivity = Intent()

                intentToMainActivity.putExtra("name", namaEdt.text.toString())
                intentToMainActivity.putExtra("nim", nimEdt.text.toString())
                intentToMainActivity.putExtra("prodi", prodiEdt.text.toString())
                intentToMainActivity.putExtra("umur", umurEdt.text.toString())
                intentToMainActivity.putExtra("hobby", hobbyEdt.text.toString())

                setResult(Activity.RESULT_OK, intentToMainActivity)
                finish()
            }
        }
    }
}
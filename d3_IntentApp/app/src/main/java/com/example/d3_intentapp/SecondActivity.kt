package com.example.d4_myshop

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

        var name = intent.getStringExtra("name")
        var data = intent.getStringExtra("data")

        with(binding){
            val mSpannableString = SpannableString(data)
            mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)

            secondText.text = "Hello, ${name}"
            detailText.text = mSpannableString
        }
    }
}
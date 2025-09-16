package com.example.d3_intentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.d3_intentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Variabel untuk menyimpan nilai terakhir
    private var currentName: String = "penguna"
    private var currentNim: String = "ini NIM anda."
    private var currentProdi: String = "-"
    private var currentUmur: String = "-"
    private var currentHobby: String = "-"

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val name = result.data?.getStringExtra("name")
                val nim = result.data?.getStringExtra("nim")
                val prodi = result.data?.getStringExtra("prodi")
                val umur = result.data?.getStringExtra("umur")
                val hobby = result.data?.getStringExtra("hobby")

                // Update hanya jika ada nilai baru
                if (!name.isNullOrBlank()) {
                    currentName = name
                }
                if (!nim.isNullOrBlank()) {
                    currentNim = nim
                }
                if (!prodi.isNullOrBlank()) {
                    currentProdi = prodi
                }
                if (!umur.isNullOrBlank()) {
                    currentUmur = umur
                }
                if (!hobby.isNullOrBlank()) {
                    currentHobby = hobby
                }

                // Tampilkan nilai terbaru
                binding.txtName.text = currentName
                binding.txtNim.text = currentNim
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnUbah.setOnClickListener {
                val intentToSecondActivity = Intent(this@MainActivity, SecondActivity::class.java)
                launcher.launch(intentToSecondActivity)
            }

            btnDetail.setOnClickListener {
                val intentToThirdActivity = Intent(this@MainActivity, ThirdActivity::class.java)
                intentToThirdActivity.putExtra("name", currentName)
                intentToThirdActivity.putExtra("nim", currentNim)
                intentToThirdActivity.putExtra("prodi", currentProdi)
                intentToThirdActivity.putExtra("umur", currentUmur)
                intentToThirdActivity.putExtra("hobby", currentHobby)
                startActivity(intentToThirdActivity)
            }

            // Set nilai awal
            txtName.text = currentName
            txtNim.text = currentNim
        }
    }
}
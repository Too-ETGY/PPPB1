package com.example.mycontact

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycontact.databinding.ActivityProfileBinding
//import com.example.mycontact.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager.getInstance(this)
//        checkLoginStatus()
        with(binding) {
            txtUsername.text = "Login sebagai: ${prefManager.getUsername()}"
            btnLogout.setOnClickListener {
                prefManager.setLoggedIn(false)
                val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
//                startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
                finish()
            }
            btnBack.setOnClickListener {
                finish()
            }
//            btnClear.setOnClickListener {
//                prefManager.clear()
//                finish()
//            }
        }
    }
//    fun checkLoginStatus() {
//        val isLoggedIn = prefManager.isLoggedIn()
//        if (!isLoggedIn) {
//            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
//            finish()
//        }
//    }
}
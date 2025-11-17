package com.example.employeeapp

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.employeeapp.databinding.ActivityMainBinding
import com.example.employeeapp.model.EmployeeResp
import com.example.employeeapp.network.ApiClient
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val client = ApiClient.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        with(binding) {
            loadEmployees()
        }
    }

    fun loadEmployees() {
        val response = client.getAllEmployees()

        response.enqueue(object : retrofit2.Callback<EmployeeResp> {
            override fun onResponse(
                call: Call<EmployeeResp?>,
                response: Response<EmployeeResp?>
            ){
                if (!response.isSuccessful) {
                    Toast.makeText(
                        this@MainActivity,
                        "HTTP ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                val body = response.body()
                val employees = body?.data.orEmpty()

                if (employees.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Data kosong", Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                val names = employees.map { it.employeeName }

                val listAdapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1,
                    names
                )

                binding.lvNama.adapter = listAdapter

                binding.lvNama.onItemClickListener= AdapterView.OnItemClickListener{ _, _, position, _ ->
                    val id = employees[position].id
                    val intent = Intent(this@MainActivity, DetailEmployeeActivity::class.java)
                    intent.putExtra("EXTRA_ID", id)
                    startActivity(intent)
                }
            }


            override fun onFailure(
                p0: Call<EmployeeResp?>,
                p1: Throwable
            )   {
                    Toast.makeText(
                        this@MainActivity, "Get Data Failed", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}
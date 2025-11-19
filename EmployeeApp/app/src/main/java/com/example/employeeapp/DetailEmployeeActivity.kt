package com.example.employeeapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.employeapp.model.UpdateResponse
import com.example.employeeapp.databinding.ActivityDetailEmployeeBinding
import com.example.employeeapp.databinding.CreateDialogBinding
import com.example.employeeapp.databinding.UpdateDialogBinding
import com.example.employeeapp.model.DetailEmployeeResp
import com.example.employeeapp.model.UpdateEmployeeReq
import com.example.employeeapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEmployeeActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDetailEmployeeBinding
    private val client= ApiClient.getInstance()

    private var employeeId = -1
    private var currentName = "-"
    private var currentSalary = "0"
    private var currentAge = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEmployeeBinding.inflate(layoutInflater)

        setContentView(binding.root)
//        with(binding) {}

        val employeeId = intent.getIntExtra("EXTRA_ID", -1)
        if (employeeId == -1) {
            Toast.makeText(
                this, "ID tidak valid", Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }
        with(binding){
            getEmployeeDetail(employeeId)
            editButton.setOnClickListener {
                showEditDialog()
            }
        }
    }

    fun getEmployeeDetail(id: Int){
        val response = client.getAllEmployeDetail(id)

        response.enqueue(object : Callback<DetailEmployeeResp>{
            override fun onResponse(
                p0: Call<DetailEmployeeResp?>,
                response: Response<DetailEmployeeResp?>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(
                        this@DetailEmployeeActivity,
                        "HTTP ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                val body = response.body()
                val employee = body?.data

                currentName = employee?.employeeName.toString()
                currentSalary = employee?.employeeSalary.toString()
                currentSalary = employee?.employeeAge.toString()

                binding.txtName.setText(currentName)
                binding.txtAge.setText(currentAge)
                binding.txtSalary.setText(currentSalary)
            }

            override fun onFailure(p0: Call<DetailEmployeeResp?>, p1: Throwable) {
                Toast.makeText(
                    this@DetailEmployeeActivity,
                    "Failed to get Detail Employee",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun showEditDialog() {
        val dialogBinding = UpdateDialogBinding.inflate(layoutInflater)

        // isi data lama
        dialogBinding.etName.setText(currentName)
        dialogBinding.etSalary.setText(currentSalary.toString())
        dialogBinding.etAge.setText(currentAge.toString())

        AlertDialog.Builder(this)
            .setTitle("Edit Employee")
            .setView(dialogBinding.root)
            .setPositiveButton("Update") { dialog, _ ->

                val newName = dialogBinding.etName.text.toString()
                val newSalary = dialogBinding.etSalary.text.toString().toInt()
                val newAge = dialogBinding.etAge.text.toString().toInt()

                updateEmployee(employeeId, newName, newSalary, newAge)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun updateEmployee(id: Int, name: String, salary: Int, age: Int) {
        val body = UpdateEmployeeReq(name, salary, age)

        client.updateEmployee(id, body)
            .enqueue(object : Callback<UpdateResponse> {
                override fun onResponse(
                    call: Call<UpdateResponse>,
                    response: Response<UpdateResponse>
                ) {

                    if (!response.isSuccessful) {
                        Toast.makeText(this@DetailEmployeeActivity, "HTTP ${response.code()}", Toast.LENGTH_SHORT).show()
                        return
                    }

                    val body = response.body()
                    if (body?.status == "success") {
                        Toast.makeText(this@DetailEmployeeActivity, "Berhasil update", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@DetailEmployeeActivity, "Update gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                    Toast.makeText(this@DetailEmployeeActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun deleteEmployee(id: Int) {
        client.deleteEmployee(id)
            .enqueue(object : Callback<DetailEmployeeResp> {
                override fun onResponse(
                    call: Call<DetailEmployeeResp>,
                    response: Response<DetailEmployeeResp>
                ) {
                    if (!response.isSuccessful) {
                        Toast.makeText(this@DetailEmployeeActivity, "Delete gagal", Toast.LENGTH_SHORT).show()
                        return
                    }

                    Toast.makeText(this@DetailEmployeeActivity, "Berhasil hapus", Toast.LENGTH_SHORT).show()
                    finish()
                }

                override fun onFailure(call: Call<DetailEmployeeResp>, t: Throwable) {
                    Toast.makeText(this@DetailEmployeeActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
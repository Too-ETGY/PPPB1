package com.example.employeeapp.model

import com.google.gson.annotations.SerializedName

data class CreateEmployeeReq(
    @SerializedName("employee_name")
    val employeName: String,

    @SerializedName("employee_age")
    val employeeAge: Int,

    @SerializedName("employee_salary")
    val employeeSalary: Int
)

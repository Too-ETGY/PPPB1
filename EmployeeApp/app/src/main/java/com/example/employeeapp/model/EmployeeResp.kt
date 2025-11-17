package com.example.employeeapp.model

import com.google.gson.annotations.SerializedName

data class EmployeeResp(
    @SerializedName("data")
    val data: List<Employee>,
)

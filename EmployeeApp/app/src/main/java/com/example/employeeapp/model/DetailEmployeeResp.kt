package com.example.employeeapp.model

import com.google.gson.annotations.SerializedName

data class DetailEmployeeResp(
    @SerializedName("data")
    val data: Employee
)

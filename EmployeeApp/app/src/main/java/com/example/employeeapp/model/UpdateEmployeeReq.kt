package com.example.employeeapp.model

import com.google.gson.annotations.SerializedName

data class UpdateEmployeeReq(
    @SerializedName("name")
    val name: String,

    @SerializedName("salary")
    val salary: Int,

    @SerializedName("age")
    val age: Int
)

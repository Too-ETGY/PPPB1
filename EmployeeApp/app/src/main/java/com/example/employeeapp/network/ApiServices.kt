package com.example.employeeapp.network

import com.example.employeeapp.model.DetailEmployeeResp
import com.example.employeeapp.model.EmployeeResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("employee/")
    fun getAllEmployees(): Call<EmployeeResp>

    @GET("employee/{id}")
    fun getAllEmployeDetail(
        @Path("id") id: Int
    ): Call<DetailEmployeeResp>
}
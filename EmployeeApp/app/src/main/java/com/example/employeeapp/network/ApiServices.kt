package com.example.employeeapp.network

import com.example.employeapp.model.UpdateResponse
import com.example.employeeapp.MainActivity
import com.example.employeeapp.model.CreateEmployeeReq
import com.example.employeeapp.model.DetailEmployeeResp
import com.example.employeeapp.model.EmployeeResp
import com.example.employeeapp.model.UpdateEmployeeReq
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.POST

interface ApiServices {

    @GET("employees")
    fun getAllEmployees(): Call<EmployeeResp>

    @GET("employee/{id}")
    fun getAllEmployeDetail(
        @Path("id") id: Int
    ): Call<DetailEmployeeResp>

    @POST("create")
    fun createEmployee(
        @Body body: CreateEmployeeReq
    ): Call<DetailEmployeeResp>

    @PATCH("update/{id}")
    fun updateEmployee(
        @Path("id") id: Int,
        @Body body: UpdateEmployeeReq
    ): Call<UpdateResponse>

    @DELETE("delete/{id}")
    fun deleteEmployee(
        @Path("id") id: Int
    ): Call<DetailEmployeeResp>
}
package com.example.employeapp.model

data class UpdateResponse(
    val status: String,
    val data: Map<String, Any>?
)

//data class UpdateData(
//    val name: String,
//    val salary: String,
//    val age: String,
//    val id: Int
//)
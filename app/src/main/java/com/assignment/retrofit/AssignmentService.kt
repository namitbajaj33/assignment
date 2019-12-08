package com.assignment.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface AssignmentService {

    @GET("bins/1gaa29")
    fun getCelebritiesCars(): Call<Any>

}
package com.assignment.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface AssignmentService {

    @GET("bins/1gaa29")
    fun getCelebritiesCars(): Call<ResponseBody>

}
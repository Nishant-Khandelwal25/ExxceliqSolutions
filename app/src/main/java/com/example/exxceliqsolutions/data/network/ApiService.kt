package com.example.exxceliqsolutions.data.network

import com.example.exxceliqsolutions.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 1
    ): ApiResponse
}
package com.phoenix.appjumpstart.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.phoenix.appjumpstart.data.model.ApiResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://d08c4cad-0e93-4095-bc3c-20faa898cf6f.mock.pstmn.io"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("/")
    suspend fun getItems(): ApiResponse
}

object RetrofitInstance {
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
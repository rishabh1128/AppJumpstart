package com.phoenix.appjumpstart.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.phoenix.appjumpstart.data.model.ApiResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://run.mocky.io/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("/v3/0d3f5911-1041-411b-ae9b-df0113e6a02c")
    suspend fun getItems(): ApiResponse
}

object RetrofitInstance {
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
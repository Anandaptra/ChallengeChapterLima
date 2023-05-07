package com.example.chapterlima.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ResultApi {
    @GET("trending/movie/week")
    fun getTrendingMovie(
        @Query("api_key") api_key: String
    ): Call<ResponseMovie>

    @GET("trending/movie/week")
    fun getDetailMovie(
        @Query("api_key") api_key: Int
    ): Call<ResponseMovie>
}
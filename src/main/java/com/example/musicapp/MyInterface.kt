package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MyInterface {

    @Headers(
        "X-RapidAPI-Key:8b0f85f6e3mshc856cd2c7cd14cep1e945ejsn2b198fd67fcc",
        "X-RapidAPI-Host:spotify23.p.rapidapi.com"
    )
    @GET("tracks/")
    fun getSearchData(@Query("ids") query: String): Call<MyData>


}
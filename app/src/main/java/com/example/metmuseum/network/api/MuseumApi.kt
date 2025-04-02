package com.example.metmuseum.network.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl("https://collectionapi.metmuseum.org/public/collection/v1/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface MuseumApi{
    @GET("search")
    suspend fun getArtObject(@Query("q") query: String): Response<ApiArtObject>

    @GET("objects/{objectID}")
    suspend fun getArtDetails(@Path("objectID") objectId: Int): Response<ApiObjectDetails>
}

val museumApi = retrofit.create(MuseumApi::class.java)
package com.example.metmuseum.network.datasource

import com.example.metmuseum.network.api.ApiArtObject
import com.example.metmuseum.network.api.ApiObjectDetails
import com.example.metmuseum.network.api.MuseumApi
import com.example.metmuseum.network.api.museumApi

class RemoteMuseumDataSourceImpl : RemoteMuseumDataSource {

    private val api: MuseumApi = museumApi

    override suspend fun getArtObject(query: String): ApiArtObject {
        if (query.isBlank()){
            return ApiArtObject(0, emptyArray())
        }
        val response = api.getArtObject(query)

        if(response.isSuccessful){
            val responseBody = response.body()
            if (responseBody != null)
            {
                return responseBody
            }
        }
        return ApiArtObject(0, emptyArray())
    }


    override suspend fun getArtDetails(objectId: Int): ApiObjectDetails {
        val response = api.getArtDetails(objectId = objectId)
        val responseBody = response.body()

        if (responseBody != null) {
            return responseBody
        } else {
            throw Exception("Response body is null")
        }
    }
}
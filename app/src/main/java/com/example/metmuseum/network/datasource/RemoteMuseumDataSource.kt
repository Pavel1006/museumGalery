package com.example.metmuseum.network.datasource

import com.example.metmuseum.network.api.ApiArtObject
import com.example.metmuseum.network.api.ApiObjectDetails

interface RemoteMuseumDataSource{
    suspend fun getArtObject(query: String): ApiArtObject
    suspend fun getArtDetails(objectId: Int): ApiObjectDetails
}
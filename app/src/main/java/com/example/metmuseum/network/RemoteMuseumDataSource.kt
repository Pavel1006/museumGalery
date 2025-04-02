package com.example.metmuseum.network

import com.example.metmuseum.network.ApiObjectDetails
import com.example.metmuseum.network.ApiArtObject


interface RemoteMuseumDataSource{
    suspend fun getArtObject(query: String): ApiArtObject
    suspend fun getArtDetails(objectId: Int): ApiObjectDetails
}
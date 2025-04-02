package com.example.metmuseum.network

interface RemoteMuseumDataSource{
    suspend fun getArtObject(query: String): ApiArtObject
    suspend fun getArtDetails(objectId: Int): ApiObjectDetails
}
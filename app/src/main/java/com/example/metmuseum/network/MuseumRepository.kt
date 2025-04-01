package com.example.metmuseum.network

import retrofit2.http.Query
import com.example.metmuseum.network.ArtObject
import com.example.metmuseum.network.ArtDetails

interface MuseumRepository{
    suspend fun getArtObject(query: String): ArtObject
    suspend fun getArtDetails(objectId: Int): ArtDetails
}

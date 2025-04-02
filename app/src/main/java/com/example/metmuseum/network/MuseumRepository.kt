package com.example.metmuseum.network

interface MuseumRepository{
    suspend fun getArtObject(query: String): ArtObject
    suspend fun getArtDetails(objectId: Int): ArtDetails
}

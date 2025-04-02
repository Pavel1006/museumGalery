package com.example.metmuseum.network.repository

import com.example.metmuseum.models.ArtDetails
import com.example.metmuseum.models.ArtObject

interface MuseumRepository{
    suspend fun getArtObject(query: String): ArtObject
    suspend fun getArtDetails(objectId: Int): ArtDetails
}

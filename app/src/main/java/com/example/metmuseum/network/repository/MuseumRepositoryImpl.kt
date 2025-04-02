package com.example.metmuseum.network.repository

import com.example.metmuseum.models.ArtDetails
import com.example.metmuseum.models.ArtObject
import com.example.metmuseum.network.datasource.RemoteMuseumDataSource
import com.example.metmuseum.network.api.toArtDetails
import com.example.metmuseum.network.api.toArtObject

class MuseumRepositoryImpl(private val remoteMuseumDataSource: RemoteMuseumDataSource):
    MuseumRepository {
    override suspend fun getArtObject(query: String): ArtObject {
        return remoteMuseumDataSource.getArtObject(query = query).toArtObject()
    }

    override suspend fun getArtDetails(objectId: Int): ArtDetails {
        return remoteMuseumDataSource.getArtDetails(objectId = objectId).toArtDetails()
    }
}
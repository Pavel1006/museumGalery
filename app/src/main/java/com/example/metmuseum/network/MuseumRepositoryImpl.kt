package com.example.metmuseum.network

import com.example.metmuseum.network.toArtObject
import com.example.metmuseum.network.toArtDetails


class MuseumRepositoryImpl(private val remoteMuseumDataSource: RemoteMuseumDataSource):
    MuseumRepository {
    override suspend fun getArtObject(query: String): ArtObject {
        return remoteMuseumDataSource.getArtObject(query = query).toArtObject()
    }

    override suspend fun getArtDetails(objectId: Int): ArtDetails {
        return remoteMuseumDataSource.getArtDetails(objectId = objectId).toArtDetails()
    }
}
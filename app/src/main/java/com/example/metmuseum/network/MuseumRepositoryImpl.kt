package com.example.metmuseum.network

class MuseumRepositoryImpl(private val remoteMuseumDataSource: RemoteMuseumDataSource):
    MuseumRepository {
    override suspend fun getArtObject(query: String): ArtObject {
        return remoteMuseumDataSource.getArtObject(query = query).toArtObject()
    }

    override suspend fun getArtDetails(objectId: Int): ArtDetails {
        return remoteMuseumDataSource.getArtDetails(objectId = objectId).toArtDetails()
    }
}
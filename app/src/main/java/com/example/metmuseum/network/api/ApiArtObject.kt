package com.example.metmuseum.network.api

import com.example.metmuseum.models.ArtObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiArtObject(
    @Json(name = "total") val total: Int,
    @Json(name = "objectIDs") val objectIDs: Array<Int>
)

fun ApiArtObject.toArtObject(): ArtObject {
    return ArtObject(
        objectIDs = objectIDs
    )
}
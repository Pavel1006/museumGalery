package com.example.metmuseum.network.api

import com.example.metmuseum.models.ArtDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiObjectDetails(
    @Json(name = "objectID") val objectID: Int,
    @Json(name = "isHighlight") val isHighlight: Boolean,
    @Json(name = "accessionNumber") val accessionNumber: String,
    @Json(name = "isPublicDomain") val isPublicDomain: Boolean,
    @Json(name = "accessionYear") val accessionYear: String,
    @Json(name = "primaryImage") val primaryImage: String,
    @Json(name = "additionalImages") val additionalImages: Array<String>,
    @Json(name = "department") val department: String,
    @Json(name = "objectName") val objectName: String,
    @Json(name = "title") val title: String,
    @Json(name = "culture") val culture: String,
    @Json(name = "period") val period: String,
    @Json(name = "dynasty") val dynasty: String,
    @Json(name = "reign") val reign: String,
    @Json(name = "portfolio") val portfolio: String,
    @Json(name = "artistRole") val artistRole: String,
    @Json(name = "artistPrefix") val artistPrefix: String,
    @Json(name = "artistDisplayName") val artistDisplayName: String,
    @Json(name = "artistDisplayBio") val artistDisplayBio: String,
    @Json(name = "artistSuffix") val artistSuffix: String,
    @Json(name = "artistAlphaSort") val artistAlphaSort: String,
    @Json(name = "artistNationality") val artistNationality: String,
    @Json(name = "artistBeginDate") val artistBeginDate: String,
    @Json(name = "artistEndDate") val artistEndDate: String,
    @Json(name = "artistGender") val artistGender: String
)

fun ApiObjectDetails.toArtDetails() : ArtDetails {
    return ArtDetails(
        objectID = objectID,
        isHighlight = isHighlight,
        accessionNumber = accessionNumber,
        isPublicDomain = isPublicDomain,
        accessionYear = accessionYear,
        primaryImage = primaryImage,
        additionalImages = additionalImages,
        department = department,
        objectName = objectName,
        title = title,
        culture = culture,
        period = period,
        dynasty = dynasty,
        reign = reign,
        portfolio = portfolio,
        artistRole = artistRole,
        artistPrefix = artistPrefix,
        artistDisplayName = artistDisplayName,
        artistDisplayBio = artistDisplayBio,
        artistSuffix = artistSuffix,
        artistAlphaSort = artistAlphaSort,
        artistNationality = artistNationality,
        artistBeginDate = artistBeginDate,
        artistEndDate = artistEndDate,
        artistGender = artistGender
    )
}
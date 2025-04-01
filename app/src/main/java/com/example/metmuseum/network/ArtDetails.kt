package com.example.metmuseum.network

data class ArtDetails(
    val objectID: Int = 0,
    val isHighlight: Boolean = false,
    val accessionNumber: String = "",
    val isPublicDomain: Boolean = false,
    val accessionYear: String = "",
    val primaryImage: String = "",
    val additionalImages: Array<String> = emptyArray(),
    val department: String = "",
    val objectName: String = "",
    val title: String = "",
    val culture: String = "",
    val period: String = "",
    val dynasty: String = "",
    val reign: String = "",
    val portfolio: String = "",
    val artistRole: String = "",
    val artistPrefix: String = "",
    val artistDisplayName: String = "",
    val artistDisplayBio: String = "",
    val artistSuffix: String = "",
    val artistAlphaSort: String = "",
    val artistNationality: String = "",
    val artistBeginDate: String = "",
    val artistEndDate: String = "",
    val artistGender: String = ""
)

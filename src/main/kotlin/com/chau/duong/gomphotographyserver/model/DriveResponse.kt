package com.chau.duong.gomphotographyserver.model

data class DriveResponse(
    val files: List<DriveFile>,
    val nextPageToken: String? = null
)
data class DriveFile(
    val id: String,
    val name: String,
    val thumbnailLink: String?,
    val webContentLink: String?
)
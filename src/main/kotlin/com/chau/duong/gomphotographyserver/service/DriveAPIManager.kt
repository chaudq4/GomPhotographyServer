package com.chau.duong.gomphotographyserver.service

import com.chau.duong.gomphotographyserver.model.DriveFile
import com.chau.duong.gomphotographyserver.model.DriveResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono


@Service
class DriveAPIManager(
    private val driveAPIService: DriveAPIService
) {

    suspend fun getListFile(
        folderId: String
    ): List<DriveFile> {
        val allFiles = mutableListOf<DriveFile>()

        var nextPageToken: String? = null

        do {

            val response = driveAPIService.getImages(
                query = buildQuery(folderId),
                pageToken = nextPageToken ?: ""
            )

            nextPageToken = response.nextPageToken

            allFiles.addAll(response.files)


        } while (nextPageToken != null)

        return allFiles
    }

    private fun buildQuery(
        folderId: String
    ): String {
        return """
            '$folderId' in parents
            and mimeType contains 'image/'
            and trashed=false
        """.trimIndent()
    }
}

@Service
class DriveAPIService(
    private val webClient: WebClient
) {
    companion object {
        const val KEY = "AIzaSyAKag7-Gd6QRBL5aHQHMp1puIwvf4p5nkA"
        const val PAGE_SIZE = 100
    }

    suspend fun getImages(
        query: String, orderBy: String = "name", pageToken: String = ""
    ): DriveResponse {
        val v = webClient.get().uri { builder ->
            builder.path("drive/v3/files").queryParam("q", query).queryParam(
                "fields", "nextPageToken,files(id,name,thumbnailLink,webContentLink)"
            ).queryParam("orderBy", orderBy).queryParam("key", KEY)
                .queryParam("pageToken", pageToken)
                .queryParam("pageSize", PAGE_SIZE)
                .build()
        }
        return v.retrieve().bodyToMono<DriveResponse>().awaitSingle()
    }
}
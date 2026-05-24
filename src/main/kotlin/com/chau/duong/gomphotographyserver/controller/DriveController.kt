package com.chau.duong.gomphotographyserver.controller

import com.chau.duong.gomphotographyserver.model.DriveFile
import com.chau.duong.gomphotographyserver.service.DriveAPIManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drive")
class DriveController(
    private val driveAPIManager: DriveAPIManager
) {

    @GetMapping("/images")
    suspend fun getImages(
        @RequestParam linkID: String
    ): List<DriveFile> {

        val folderId = getIdFolder(linkID)
        return  driveAPIManager.getListFile(folderId)
    }

     fun getIdFolder(url: String): String {
        return url.substringAfter("/folders/").substringBefore("?")
    }
}
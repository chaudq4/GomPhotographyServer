package com.chau.duong.gomphotographyserver.controller

import com.chau.duong.gomphotographyserver.entity.Album
import com.chau.duong.gomphotographyserver.entity.AlbumUser
import com.chau.duong.gomphotographyserver.entity.User
import com.chau.duong.gomphotographyserver.service.AlbumUserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/albumusers")
class AlbumUserController(private val albumUserService: AlbumUserService) {

    @PostMapping("/add")
    fun addAlbumUser(albumUser: AlbumUser) {
        albumUserService.addAlbumUser(albumUser)
    }

    @PostMapping("/remove")
    fun removeAlbumUser(albumUser: AlbumUser) {
        albumUserService.removeAlbumUser(albumUser)
    }

    @PostMapping("/get")
    fun getAlbumByUser(@RequestBody user: User): List<Album> {
        return albumUserService.getAlbumByUser(user)
    }

}
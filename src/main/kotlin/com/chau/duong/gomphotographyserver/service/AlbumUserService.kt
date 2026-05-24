package com.chau.duong.gomphotographyserver.service

import com.chau.duong.gomphotographyserver.controller.UserController
import com.chau.duong.gomphotographyserver.entity.Album
import com.chau.duong.gomphotographyserver.entity.AlbumUser
import com.chau.duong.gomphotographyserver.entity.User
import com.chau.duong.gomphotographyserver.repository.AlbumUserRepository
import org.springframework.stereotype.Service

@Service
class AlbumUserService(
    private val albumUserRepository: AlbumUserRepository,
    private val userController: UserController
) {
    fun addAlbumUser(albumUser: AlbumUser) {
        albumUserRepository.save(albumUser)
    }

    fun removeAlbumUser(albumUser: AlbumUser) {
        albumUserRepository.deleteByUserIdAndAlbumId(albumUser.user?.id ?: 0, albumUser.album?.id ?: 0)
    }

    fun getAlbumByUser(user: User): List<Album> {
        val userResult = userController.getUserById(user.id)
        userResult?.albumUsers
        val result = mutableListOf<Album>()
        userResult?.albumUsers?.forEach {
            it.album?.let { album ->
                result.add(album)
            }
        }
        return result
    }
}
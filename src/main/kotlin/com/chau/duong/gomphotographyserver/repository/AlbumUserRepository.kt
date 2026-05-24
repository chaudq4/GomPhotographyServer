package com.chau.duong.gomphotographyserver.repository

import com.chau.duong.gomphotographyserver.entity.AlbumUser
import org.springframework.data.jpa.repository.JpaRepository

interface AlbumUserRepository :
    JpaRepository<AlbumUser, Long> {

    fun deleteByUserIdAndAlbumId(
        userId: Long,
        albumId: Long
    )

}
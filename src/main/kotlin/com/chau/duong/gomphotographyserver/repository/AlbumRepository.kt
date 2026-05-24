package com.chau.duong.gomphotographyserver.repository

import com.chau.duong.gomphotographyserver.entity.Album
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AlbumRepository : JpaRepository<Album, Long> {


    @Modifying
    @Transactional
    @Query(
        value = """ INSERT INTO favorite(album_id, value) VALUES (:albumId, :value)""",
        nativeQuery = true
    )
    fun addFavorite(
        @Param("albumId") albumId: Long,
        @Param("value") value: String
    )

    @Modifying
    @Transactional
    @Query(
        value = """ INSERT INTO selected(album_id, value) VALUES (:albumId, :value)""",
        nativeQuery = true
    )
    fun addSelected(
        @Param("albumId") albumId: Long,
        @Param("value") value: String
    )

    @Modifying
    @Transactional
    @Query(
        value = """DELETE FROM favorite WHERE album_id = :albumId AND value = :value """,
        nativeQuery = true
    )
    fun removeFavorite(
        @Param("albumId") albumId: Long,
        @Param("value") value: String
    )

    @Modifying
    @Transactional
    @Query(
        value = """DELETE FROM selected WHERE album_id = :albumId AND value = :value """,
        nativeQuery = true
    )
    fun removeSelected(
        @Param("albumId") albumId: Long,
        @Param("value") value: String
    )

}
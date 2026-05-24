package com.chau.duong.gomphotographyserver.service

import com.chau.duong.gomphotographyserver.entity.Album
import com.chau.duong.gomphotographyserver.entity.User
import com.chau.duong.gomphotographyserver.repository.AlbumRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AlbumService(private val albumRepository: AlbumRepository) {


    fun addFavorite(idAlbum: Long, item: String) = albumRepository.addFavorite(idAlbum, item)
    fun removeFavorite(idAlbum: Long, itemRemove: String) = albumRepository.removeFavorite(idAlbum, itemRemove)
    fun addSelected(idAlbum: Long, item: String) = albumRepository.addSelected(idAlbum, item)
    fun removeSelected(idAlbum: Long, itemRemove: String) = albumRepository.removeSelected(idAlbum, itemRemove)

    @Transactional
    fun updateLinkOriginal(albumId: Long, link: String) {
        val album = albumRepository.findById(albumId)
            .orElseThrow()
        album.linkOriginal = link
    }

    @Transactional
    fun updateLinkEdit(albumId: Long, linkEdit: String) {
        val album = albumRepository.findById(albumId)
            .orElseThrow()
        album.linkEdit = linkEdit
    }
    fun getAllAlbums(): List<Album> {
        return albumRepository.findAll()
    }

}
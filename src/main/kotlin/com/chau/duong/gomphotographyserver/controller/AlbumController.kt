package com.chau.duong.gomphotographyserver.controller

import com.chau.duong.gomphotographyserver.entity.Album
import com.chau.duong.gomphotographyserver.entity.User
import com.chau.duong.gomphotographyserver.repository.AlbumRepository
import com.chau.duong.gomphotographyserver.repository.UserRepository
import com.chau.duong.gomphotographyserver.service.AlbumService
import com.chau.duong.gomphotographyserver.service.DriveAPIManager
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClientResponseException

@RestController
class AlbumController(
    private val albumService: AlbumService,
    private val albumRepository: AlbumRepository,
    private val driveController: DriveController,

    ) {
    val coroutine = CoroutineScope(Dispatchers.IO)
    @GetMapping("/albums")
    fun getAllAlbum(
    ): List<Album> {
        return albumService.getAllAlbums()
    }
    @GetMapping("/albums/syncall")
    suspend fun syncAllAlbum() {
        val listAlbum = albumService.getAllAlbums()
            println("----------------start sync ------------------")
           val jobs =  listAlbum.map { album ->
                coroutine.async {
                    try {
                        println("sync album ${album.id} ${album.name} ${album.linkOriginal}")
                        syncLinkDriveAlbum(album)
                    } catch (e: WebClientResponseException) {
                        println(e.message)
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            }
        jobs.awaitAll()
        println("--------------end sync ---------------")
    }

    @PostMapping("/albums/sync")
    suspend fun syncLinkDriveAlbum(@RequestBody album: Album) {
        val gson = Gson()
        val listDriveFile = driveController.getImages(album.linkOriginal)
        val jsonListDriveFile = gson.toJson(listDriveFile)
        jsonListDriveFile?.let {
            album.listDriveFileJSon = jsonListDriveFile
        }
        if (album.linkEdit.isNotEmpty()) {
            val listEditFile = driveController.getImages(album.linkEdit)
            val jsonListEditFile = gson.toJson(listEditFile)
            jsonListEditFile?.let {
                album.listEditFileJSon = jsonListEditFile
            }
            println("linkEdit: ${album.name}   listEditFile: ${listEditFile.size} ")
        }
        println("linkOriginal: ${album.name}   listDriveFile: ${listDriveFile.size} ")

        withContext(Dispatchers.IO) {
            albumRepository.save(album)
        }
    }


    @PostMapping("/albums/add")
    fun addAlbum(@RequestBody album: Album) {
        coroutine.launch {
            syncLinkDriveAlbum(album)
        }
    }

    @PostMapping("/favorite/add/{id}")
    fun addFavorite(@PathVariable id: Long, @RequestParam item: String) {
        albumService.addFavorite(id, item)
    }
    @PostMapping("/favorite/remove/{id}")
    fun removeFavorite(@PathVariable id: Long, @RequestParam item: String) {
        albumService.removeFavorite(id, item)
    }
    @PostMapping("/selected/add/{id}")
    fun addSelected(@PathVariable id: Long, @RequestParam item: String) {
        albumService.addSelected(id, item)
    }
    @PostMapping("/selected/remove/{id}")
    fun removeSelected(@PathVariable id: Long, @RequestParam item: String) {
        albumService.removeSelected(id, item)
    }
    fun updateLinkOriginal(idAlbum: Long, linkOriginal: String) {
        albumService.updateLinkOriginal(idAlbum, linkOriginal)
    }

    fun updateLinkEdit(idAlbum: Long, linkEdit: String) {
        albumService.updateLinkEdit(idAlbum, linkEdit)
    }

}
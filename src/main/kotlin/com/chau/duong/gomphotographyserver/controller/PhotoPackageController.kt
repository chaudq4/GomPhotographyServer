package com.chau.duong.gomphotographyserver.controller

import com.chau.duong.gomphotographyserver.entity.PhotoPackage
import com.chau.duong.gomphotographyserver.service.PhotoPackageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/photopackage")
@RestController
class PhotoPackageController(private val photoPackageService: PhotoPackageService) {
    @PostMapping("/add")
    fun addPhotoPackage(@RequestBody photoPackage: PhotoPackage) = photoPackageService.addPhotoPackage(photoPackage)
    @GetMapping("/get")
    fun getAllPhotoPackage():List<PhotoPackage> = photoPackageService.getAllPhotoPackage()
}
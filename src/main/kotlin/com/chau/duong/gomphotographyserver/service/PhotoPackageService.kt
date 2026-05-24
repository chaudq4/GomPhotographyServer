package com.chau.duong.gomphotographyserver.service

import com.chau.duong.gomphotographyserver.entity.PhotoPackage
import com.chau.duong.gomphotographyserver.repository.PhotoPackageRepository
import org.springframework.stereotype.Service

@Service
class PhotoPackageService(private val photoPackageRepository: PhotoPackageRepository) {
    fun addPhotoPackage(photoPackage: PhotoPackage) = photoPackageRepository.save(photoPackage)
    fun getAllPhotoPackage(): List<PhotoPackage>  = photoPackageRepository.findAll()
}
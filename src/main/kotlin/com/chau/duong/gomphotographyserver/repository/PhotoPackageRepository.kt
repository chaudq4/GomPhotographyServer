package com.chau.duong.gomphotographyserver.repository

import com.chau.duong.gomphotographyserver.entity.PhotoPackage
import org.springframework.data.jpa.repository.JpaRepository

interface PhotoPackageRepository : JpaRepository<PhotoPackage, Long> {

}
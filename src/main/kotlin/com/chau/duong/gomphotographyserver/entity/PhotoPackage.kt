package com.chau.duong.gomphotographyserver.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "photo_package")
data class PhotoPackage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // Đám cưới / Cá nhân / Doanh nghiệp
    val catalog: String = "",

    val name: String = "",

    @Column(columnDefinition = "TEXT")
    val description: String = "",

    val price: Long = 0,

    val durationHours: Int = 0,

    val editedPhotos: Int = 0,

    val rawPhotos: Boolean = false,

    val makeupIncluded: Boolean = false,

    val outdoor: Boolean = false,

    val videoIncluded: Boolean = false,

    val thumbnail: String = ""
)
package com.chau.duong.gomphotographyserver.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "album")
data class Album(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val date: String = "",
    val name: String = "",

    @Column(columnDefinition = "TEXT")
    var linkOriginal: String = "",

    @Column(columnDefinition = "TEXT")
    var linkEdit: String = "",

    @ElementCollection
    @CollectionTable(
        name = "favorite",
        joinColumns = [JoinColumn(name = "album_id")]
    )
    @Column(name = "value")
    val favorites: List<String> = emptyList(),

    @ElementCollection
    @CollectionTable(
        name = "selected",
        joinColumns = [JoinColumn(name = "album_id")]
    )
    @Column(name = "value")
    val selecteds: List<String> = emptyList(),

    @Column(columnDefinition = "TEXT")
    var listDriveFileJSon: String = "",

    @Column(columnDefinition = "TEXT")
    var listEditFileJSon: String = ""

)
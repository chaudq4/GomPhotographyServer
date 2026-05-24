package com.chau.duong.gomphotographyserver.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

data class UserDTO(
    val id: Long = 0,

    val username: String = "",

    val password: String = "",

    val name: String = "",

    val email: String = "",
    val phone: String = ""
)
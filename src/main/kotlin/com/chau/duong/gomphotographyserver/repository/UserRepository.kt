package com.chau.duong.gomphotographyserver.repository

import com.chau.duong.gomphotographyserver.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

    fun findByUsername(
        username: String
    ): User?

    fun findByEmail(
        email: String
    ): User?

    fun existsByUsername(
        username: String
    ): Boolean

    fun findUserById(userId: Long): User?
}
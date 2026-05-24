package com.chau.duong.gomphotographyserver.service

import com.chau.duong.gomphotographyserver.entity.User
import com.chau.duong.gomphotographyserver.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUserById(userId: Long): User? {
        return userRepository.findUserById(userId)
    }

    fun getUserByUsername(
        username: String
    ): User? {
        println("findByUsername $username")
        return userRepository.findByUsername(username)
    }

    fun createUser(
        user: User
    ): User {
        if (userRepository.existsByUsername(user.username)) {
            throw DuplicateUsernameException("Username already exists")
        }
        return userRepository.save(user)
    }

    class DuplicateUsernameException(message: String) : RuntimeException(message)
}
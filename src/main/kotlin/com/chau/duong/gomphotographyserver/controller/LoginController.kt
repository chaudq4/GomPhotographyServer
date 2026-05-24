package com.chau.duong.gomphotographyserver.controller

import com.chau.duong.gomphotographyserver.entity.User
import com.chau.duong.gomphotographyserver.entity.UserDTO
import com.chau.duong.gomphotographyserver.entity.toDTO
import com.chau.duong.gomphotographyserver.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(private val userService: UserService) {
    @PostMapping("/login")
    fun login(
        @RequestBody user: User
    ):  UserDTO {
        val result = userService.getUserByUsername(user.username)
        println("login $user result: ${result.toString()}")
        if (result?.password.equals(user.password) && result != null) {
            println("login -------- ${result.toDTO()}}")
            return result.toDTO()
        }
        println("login xxxxxx}")
        return UserDTO()
    }

    @GetMapping("/")
    fun welcome(): String {
        return "Welcome back!"
    }

}
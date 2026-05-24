package com.chau.duong.gomphotographyserver.controller

import com.chau.duong.gomphotographyserver.entity.User
import com.chau.duong.gomphotographyserver.service.UserService
import com.chauduong.gomphotographer.model.UserSocketEvent
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val messagingTemplate: SimpMessagingTemplate
) {

    @GetMapping
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): User? {
        return userService.getUserById(id)
    }

    @GetMapping("/{username}")
    fun getUser(
        @PathVariable username: String
    ): User? {
        return userService.getUserByUsername(username)
    }

    @PostMapping("/create")
    fun createUser(
        @RequestBody user: User
    ): User {
        return userService.createUser(user)
    }

    fun notification(
    ) {
        messagingTemplate.convertAndSend(
            "/topic/user",
            UserSocketEvent(
                type = "NEW_USER"
            )
        )
    }
}
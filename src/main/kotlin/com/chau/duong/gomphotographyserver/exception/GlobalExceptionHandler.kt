package com.chau.duong.gomphotographyserver.exception

import com.chau.duong.gomphotographyserver.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UserService.DuplicateUsernameException::class)
    fun handleDuplicate(ex: UserService.DuplicateUsernameException): ResponseEntity<String> {
        println("handleDuplicate")
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ex.message)
    }
}
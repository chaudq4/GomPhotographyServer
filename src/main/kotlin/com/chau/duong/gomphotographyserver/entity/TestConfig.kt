package com.chau.duong.gomphotographyserver.entity

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TestConfig(
    @Value("\${spring.datasource.password}")
    val password: String
) {

    @PostConstruct
    fun test() {
        println("PASSWORD = $password")
    }
}
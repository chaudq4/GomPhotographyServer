package com.chau.duong.gomphotographyserver

import com.chauduong.gomphotographer.model.UserSocketEvent
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.messaging.simp.SimpMessagingTemplate

@SpringBootApplication
class GomPhotographyServerApplication

fun main(args: Array<String>) {
    val context = runApplication<GomPhotographyServerApplication>(*args)
    println("Server is running...")
//    val messagingTemplate =
//        context.getBean(SimpMessagingTemplate::class.java)
//    while (true) {
//
//        messagingTemplate.convertAndSend(
//            "/topic/user",
//            UserSocketEvent(
//                type = "PING"
//            )
//        )
//        println("convertAndSend")
//        Thread.sleep(3000)
//    }

}


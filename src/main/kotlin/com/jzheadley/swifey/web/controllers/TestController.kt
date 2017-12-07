package com.jzheadley.swifey.web.controllers

import de.bytefish.fcmjava.client.FcmClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/test")
class TestController(private val fcmClient: FcmClient) {

//    @GetMapping("/fcm")
//    fun sendFcm() {
//        var messageOptions = FcmMessageOptions.builder()
//                .setTimeToLive(Duration.ofDays(1))
//                .build()
//        val notification: NotificationMulticastMessage = NotificationMulticastMessage(messageOptions, )
//        fcmClient.send(notification)
//    }


}
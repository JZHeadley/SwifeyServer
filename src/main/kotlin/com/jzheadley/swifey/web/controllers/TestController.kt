package com.jzheadley.swifey.web.controllers

import de.bytefish.fcmjava.client.FcmClient
import de.bytefish.fcmjava.model.options.FcmMessageOptions
import de.bytefish.fcmjava.requests.notification.NotificationMulticastMessage
import de.bytefish.fcmjava.requests.notification.NotificationPayload
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration


@RestController
@RequestMapping("/api/test")
class TestController(private val fcmClient: FcmClient) {

    @GetMapping("/fcm")
    fun sendFcm() {
        var messageOptions = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofDays(1))
                .build()
        val notification: NotificationMulticastMessage = NotificationMulticastMessage(messageOptions,
                listOf("cDjnoMy4Ges:APA91bEJZHnVUiUleFLt-a2a7EGlnDVDDHbDvE8KpWfbg8Qoje0-h_cOAhgFD91omSQVF-z9Zw1af5nzohp6ZkoH3jUiNhbGdDBxkotlbWeNPASr26x3MxRVabz3KN_GbEcN-tvl-py4"),
                NotificationPayload.builder().setBody("Test MEssage sent from the server").setTitle("Testing The Server Sending").build())
        fcmClient.send(notification)

    }
}
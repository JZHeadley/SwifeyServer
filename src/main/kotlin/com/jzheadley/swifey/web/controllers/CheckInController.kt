package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.CheckIn
import com.jzheadley.swifey.domain.User
import com.jzheadley.swifey.domain.payloads.CheckInPayload
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.utils.FromDTO.checkInToDTO
import com.jzheadley.swifey.web.repositories.CheckInRepository
import com.jzheadley.swifey.web.repositories.UserRepository
import de.bytefish.fcmjava.client.FcmClient
import de.bytefish.fcmjava.model.options.FcmMessageOptions
import de.bytefish.fcmjava.requests.data.DataMulticastMessage
import de.bytefish.fcmjava.requests.notification.NotificationMulticastMessage
import de.bytefish.fcmjava.requests.notification.NotificationPayload
import de.bytefish.fcmjava.responses.FcmMessageResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Duration
import java.util.*

@RestController
@RequestMapping("/api/checkins")
class CheckInController(private val checkInRepository: CheckInRepository, private val userRepository: UserRepository, private val fcmClient: FcmClient) {
    @GetMapping("/")
    fun getAllCheckIns(): ResponseEntity<MutableList<CheckIn>> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.findAll()))

    @GetMapping("/{id}")
    fun getCheckInById(id: Int): ResponseEntity<CheckIn> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.findById(id)))

    @PostMapping("/{id}/close")
    fun closeCheckInForOrders(@PathVariable("id") id: Int) {
        val checkIn: CheckIn? = checkInRepository.findById(id)
        if (checkIn?.acceptingOrders!!) {
            sendNotificationOfCheckInClosure(checkIn)
            return checkInRepository.closeCheckIn(id)
        }
    }

    @PostMapping("/")
    fun createCheckIn(@RequestBody checkIn: CheckIn): ResponseEntity<CheckIn> {
        // Equivalent call if worked how I was counting on them too...
        // Relevant SQL is in the CheckInRepository
//        checkInRepository.save(checkIn.restaurantCheckedInAt.restaurantId, checkIn.maxNumOrders, checkIn.checkedInUser.userId)

        //Logic to handle sending messages to the correct people.
        checkIn.acceptingOrders = true
        val savedCheckIn = checkInRepository.save(checkIn)
        sendNotifsOfCheckInToFollowersOfUser(savedCheckIn.checkedInUser!!.userId, savedCheckIn)
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(savedCheckIn))

    }

    private fun sendNotifsOfCheckInToFollowersOfUser(userId: String, checkIn: CheckIn) {
        val user: User = userRepository.findById(userId)!!
        val messageOptions = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofHours(1))
                .build()
        val followers: List<User> = userRepository.findFollowersOfUser(userId)
        val messagingingIds: List<String> = followers.map { follower -> follower.messagingId }
        val notification = DataMulticastMessage(messageOptions,
                messagingingIds,
                CheckInPayload(checkInToDTO(checkIn)),
                NotificationPayload.builder()
                        .setClickAction("RESTAURANT_MENU_FROM_CHECKIN")
                        .setBody(user.name + " has checked in at " + checkIn.restaurantCheckedInAt!!.restaurantName + ".  Place an order Now!")
                        .setTitle("A Swifey has checked in")
                        .build())
        val response: FcmMessageResponse = fcmClient.send(notification)
        println("FCM Message Response:\t" + response)
    }

    private fun sendNotificationOfCheckInClosure(checkIn: CheckIn?) {
        val messageOptions = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofHours(1))
                .build()
        val messagingingIds: List<String> = checkInRepository.getMessagingIdsOfCheckInOrderers(checkIn?.checkInId!!)
        val notification = NotificationMulticastMessage(messageOptions,
                messagingingIds,
                NotificationPayload.builder()
                        .setBody("Pick up your order from " + checkIn.checkedInUser!!.name + " at " + checkIn.restaurantCheckedInAt?.restaurantName)
                        .setTitle("Pick Up Your Order Now")
                        .build())
        val response: FcmMessageResponse = fcmClient.send(notification)
        println("FCM Message Response:\t" + response)
    }
}
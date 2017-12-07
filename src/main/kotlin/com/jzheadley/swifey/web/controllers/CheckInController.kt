package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.CheckIn
import com.jzheadley.swifey.domain.User
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.repositories.CheckInRepository
import com.jzheadley.swifey.web.repositories.UserRepository
import de.bytefish.fcmjava.client.FcmClient
import de.bytefish.fcmjava.model.options.FcmMessageOptions
import de.bytefish.fcmjava.requests.data.DataMulticastMessage
import de.bytefish.fcmjava.requests.notification.NotificationPayload
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


    @GetMapping("/user/{userid}")
    fun getCheckInById(userId: String): ResponseEntity<MutableList<CheckIn>> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.findByUserId(userId)))


    @PostMapping("/")
    fun createCheckIn(@RequestBody checkIn: CheckIn): ResponseEntity<CheckIn> {
        // Equivalent call if worked how I was counting on them too...
        // Relevant SQL is in the CheckInRepository
//        checkInRepository.save(checkIn.restaurantCheckedInAt.restaurantId, checkIn.maxNumOrders, checkIn.checkedInUser.userId)

        //Logic to handle sending messages to the correct people.
        sendNotifsToFollowersOfUser(checkIn.checkedInUser!!.userId, checkIn)
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.save(checkIn)))

    }

    private fun sendNotifsToFollowersOfUser(userId: String, checkIn: CheckIn) {
        val user: User = userRepository.findById(userId)!!
        val messageOptions = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofHours(1))
                .build()
        val followers: List<User> = userRepository.findFollowersOfUser(userId)
        val messagingingIds: List<String> = followers.map { follower -> follower.messagingId }
        val notification = DataMulticastMessage(messageOptions,
                messagingingIds,
                checkIn,
                NotificationPayload.builder()
                        .setBody(user.name + " has checked in at " + checkIn.restaurantCheckedInAt!!.restaurantName + ".  Place an order Now!")
                        .setTitle("A Swifey has checked in")
                        .build())
        fcmClient.send(notification)
    }
}
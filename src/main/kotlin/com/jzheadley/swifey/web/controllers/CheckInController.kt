package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.CheckIn
import com.jzheadley.swifey.domain.User
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.repositories.CheckInRepository
import com.jzheadley.swifey.web.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/checkins")
class CheckInController(val checkInRepository: CheckInRepository, val userRepository: UserRepository) {
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
        sendNotifsToFollowersOfUser(checkIn.checkedInUser!!.userId)
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.save(checkIn)))

    }

    private fun sendNotifsToFollowersOfUser(userId: String) {
        val followers: List<User> = userRepository.findFollowersOfUser(userId)
    }
}
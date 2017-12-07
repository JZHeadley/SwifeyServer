package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.CheckIn
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.repositories.CheckInRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/checkins")
class CheckInController(val checkInRepository: CheckInRepository) {
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
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.save(checkIn)))

    }
}
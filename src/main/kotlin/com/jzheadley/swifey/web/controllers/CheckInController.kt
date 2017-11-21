package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.CheckIn
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.repositories.CheckInRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/checkins")
class CheckInController(val checkInRepository: CheckInRepository) {
    @GetMapping("/")
    fun getAllCheckIns(): ResponseEntity<MutableList<CheckIn>> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.findAll()))

    @GetMapping("/{id}")
    fun getCheckInById(id: Int): ResponseEntity<CheckIn> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.findById(id)))
}
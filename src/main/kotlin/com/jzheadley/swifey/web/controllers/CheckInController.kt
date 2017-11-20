package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.web.repositories.CheckInRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/checkins")
class CheckInController(val checkInRepository: CheckInRepository) {
    @GetMapping("/")
    fun getAllCheckIns() = checkInRepository.findAll()
}
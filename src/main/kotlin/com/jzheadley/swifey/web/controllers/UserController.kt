package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.User
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(val userRepository: UserRepository) {
    @GetMapping("/")
    fun getAllUsers(): ResponseEntity<List<User>> = ResponseEntity.ok(userRepository.findAll())

    @GetMapping("/{id}")
    fun getUSerById(userId: String): ResponseEntity<User> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(userRepository.findById(userId)))
}
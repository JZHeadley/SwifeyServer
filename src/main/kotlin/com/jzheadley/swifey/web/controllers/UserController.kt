package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.CheckIn
import com.jzheadley.swifey.domain.User
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.repositories.CheckInRepository
import com.jzheadley.swifey.web.repositories.PhoneRepository
import com.jzheadley.swifey.web.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.constraints.Null

@RestController
@RequestMapping("/api/users")
class UserController(val userRepository: UserRepository, val phoneRepository: PhoneRepository, val checkInRepository: CheckInRepository) {
    @GetMapping("/")
    fun getAllUsers(): ResponseEntity<List<User>> = ResponseEntity.ok(userRepository.findAll())

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: String): ResponseEntity<User> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(userRepository.findById(userId)))

    @GetMapping("/search/{searchString}")
    fun searchUser(@PathVariable("searchString")searchString:String) = ResponseUtil.wrapOrNotFound(Optional.ofNullable(userRepository.findUserLike(searchString)))

    @GetMapping("/userid/{userid}/checkIns")
    fun getCheckInById(@PathVariable ("userid") userid:String) = ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.findByUserId(userid)))


    @PostMapping("/")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        // Below is what would be if The saves returned what I needed
//        val phone = phoneRepository.save(user.phone)
//        println("The saved phone was:\t" + phone)
//        userRepository.save(user.userId, user.firstName, user.lastName, user.dob, user.numSwipes, phone)
        // This saves both at once in one call.  The mostly equivalent sql is in the repositories
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userRepository.save(user)))
    }

    @PostMapping("/{id}/messagingId/{messagingId}")
    fun setUserMessagingId(@PathVariable("id") userId: String, @PathVariable("messagingId") messagingId: String) = userRepository.setUserMessagingId(userId, messagingId)
}
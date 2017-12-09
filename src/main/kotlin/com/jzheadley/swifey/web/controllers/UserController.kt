package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.Following
import com.jzheadley.swifey.domain.User
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.utils.FromDTO.checkInToDTO
import com.jzheadley.swifey.utils.FromDTO.userToDTO
import com.jzheadley.swifey.web.repositories.CheckInRepository
import com.jzheadley.swifey.web.repositories.PhoneRepository
import com.jzheadley.swifey.web.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(val userRepository: UserRepository, val phoneRepository: PhoneRepository, val checkInRepository: CheckInRepository) {
    @GetMapping("/")
    fun getAllUsers(): ResponseEntity<List<User>> = ResponseEntity.ok(userRepository.findAll())

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: String): ResponseEntity<User> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(userRepository.findById(userId)))

    @GetMapping("/search/{searchString}")
    fun searchUser(@PathVariable("searchString") searchString: String) = ResponseUtil.wrapOrNotFound(Optional.ofNullable(userRepository.findUserLike(searchString)
            .map { user -> userToDTO(user) }))

    @GetMapping("/userid/{userId}/checkIns")
    fun getCheckInById(@PathVariable("userId") userid: String) = ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkInRepository.findByUserId(userid)
    !!.map { checkIn -> checkInToDTO(checkIn) }))

    @GetMapping("/{id}/follows")
    fun getFollowersOfUser(@PathVariable("id")id:String) = ResponseUtil.wrapOrNotFound(Optional.ofNullable(userRepository.findFollowersOfUser(id)))


    @PostMapping("/following")
    fun addFollowing (@RequestBody following: Following): ResponseEntity<Int> {
        //todo: return some diffrent

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userRepository.createFollowing(following.userId, following.follower.userId)))

    }


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
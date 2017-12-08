package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.CheckIn
import com.jzheadley.swifey.domain.Following
import com.jzheadley.swifey.domain.Restaurant
import com.jzheadley.swifey.domain.User
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.dto.CheckInDTO
import com.jzheadley.swifey.web.dto.RestaurantDTO
import com.jzheadley.swifey.web.dto.UserDTO
import com.jzheadley.swifey.web.repositories.CheckInRepository
import com.jzheadley.swifey.web.repositories.PhoneRepository
import com.jzheadley.swifey.web.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.format.TextStyle
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

    fun checkInToDTO(checkIn: CheckIn): CheckInDTO {
        return CheckInDTO(checkIn.checkInId,
                checkIn.maxNumOrders,
                userToDTO(checkIn.checkedInUser),
                restaurantToDTO(checkIn.restaurantCheckedInAt),
                checkIn.orders)
    }

    fun userToDTO(user: User?): UserDTO {
        return UserDTO(user?.userId, user?.firstName, user?.lastName,
                user?.dob, user?.numSwipes, user?.messagingId, user?.phone)
    }

    fun restaurantToDTO(restaurant: Restaurant?): RestaurantDTO {
        val dayOfTheWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val hours = restaurant?.hours?.filter { restaurantHours -> restaurantHours.dayOfWeek.equals(dayOfTheWeek.toUpperCase()) }?.first()
//        println("Today's hours are:\t" + hours)
        return RestaurantDTO(restaurant?.restaurantId,
                restaurant?.restaurantName,
                restaurant?.restaurantPhotoUrl,
//                restaurant.restaurantDescription,
                restaurant?.address,
                restaurant?.phone,
                hours, restaurant?.swipeTimes)
    }
}
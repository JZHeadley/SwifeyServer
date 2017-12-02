package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.*
import com.jzheadley.swifey.web.repositories.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.Date
import java.sql.Timestamp
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("/api/data")
class DataController(val userRepository: UserRepository, val phoneRepository: PhoneRepository, val addressRepository: AddressRepository, val restaurantRepository: RestaurantRepository, val checkInRepository: CheckInRepository) {
    @GetMapping("/create")
    fun createData(): ResponseEntity<Void> {
        val phone1 = Phone(null, 1, 540, 834, 7842)
        val phone2 = Phone(null, 1, 540, 775, 2365)
        val address = Address(null, 7331, "Buchanan Dr.", "King George", "Virginia", 22485)
        val user1 = User(UUID.randomUUID().toString(), "Jonathon,", "Headley", Date(1997, 7, 23), Timestamp.from(Instant.now()), 300, phone1, null, null, null)
        val user2 = User(UUID.randomUUID().toString(), "Jonathon,", "Headley", Date(1996, 8, 21), Timestamp.from(Instant.now()), 300, phone1, null, setOf(user1), null)
        val restaurant = Restaurant(null, "Potato Restaurant", "", "", address, phone2, mutableSetOf())
        val checkIn = CheckIn(null, Timestamp.from(Instant.now()), 6, user1, restaurant, setOf())
        phoneRepository.save(phone1)

        phoneRepository.save(phone2)
        addressRepository.save(address)
        userRepository.save(user1)
        userRepository.save(user2)
        restaurantRepository.save(restaurant)
        checkInRepository.save(checkIn)
        return ResponseEntity.ok().build()
    }
}
package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.web.repositories.RestaurantRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/restaurants")
class RestaurantController(val restaurantRepository: RestaurantRepository) {
    @GetMapping("/")
    fun getAllRestaurants() = restaurantRepository.findAll()
}
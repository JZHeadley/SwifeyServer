package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.Restaurant
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.repositories.RestaurantRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/restaurants")
class RestaurantController(val restaurantRepository: RestaurantRepository) {
    @GetMapping("/")
    fun getAllRestaurants(): ResponseEntity<MutableList<Restaurant>> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurantRepository.findAll()))

    @GetMapping("/{id}")
    fun getRestaurantById(id: Int): ResponseEntity<Restaurant> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurantRepository.findById(id)))
}
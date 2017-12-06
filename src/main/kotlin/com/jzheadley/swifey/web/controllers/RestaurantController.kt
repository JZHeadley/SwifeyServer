package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.Restaurant
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.dto.RestaurantDTO
import com.jzheadley.swifey.web.repositories.RestaurantRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@RestController
@RequestMapping("/api/restaurants")
class RestaurantController(val restaurantRepository: RestaurantRepository) {
    @GetMapping("/")
    fun getAllRestaurants(): ResponseEntity<MutableList<Restaurant>> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurantRepository.findAll()))

    @GetMapping("/{id}")
    fun getRestaurantById(id: Int): ResponseEntity<Restaurant> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurantRepository.findById(id)))

    @GetMapping("/today")
    fun getTodaysRestaurants(): ResponseEntity<List<RestaurantDTO>> {
        var restaurants = restaurantRepository.findByDayOfWeek(LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH))?.map { restaurant -> restaurantToDTO(restaurant) }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurants))
    }

    fun restaurantToDTO(restaurant: Restaurant): RestaurantDTO {
        val dayOfTheWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val hours = restaurant.hours.filter { restaurantHours -> restaurantHours.dayOfWeek.equals(dayOfTheWeek.toUpperCase()) }.first()
        println("Today's hours are:\t" + hours)
        return RestaurantDTO(restaurant.restaurantId,
                restaurant.restaurantName,
                restaurant.restaurantPhotoUrl,
//                restaurant.restaurantDescription,
                restaurant.address,
                restaurant.phone,
                hours)
    }

}
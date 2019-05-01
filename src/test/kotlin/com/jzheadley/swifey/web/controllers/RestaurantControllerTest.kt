package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.Address
import com.jzheadley.swifey.domain.Phone
import com.jzheadley.swifey.domain.Restaurant
import com.jzheadley.swifey.domain.RestaurantHours
import com.jzheadley.swifey.web.repositories.MealRepository
import com.jzheadley.swifey.web.repositories.RestaurantRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.sql.Time
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

//@RunWith(SpringRunner::class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner::class)
class RestaurantControllerTest {
    @InjectMocks
    lateinit var restaurantController: RestaurantController
    @Mock
    lateinit var restaurantRepository: RestaurantRepository
    @Mock
    lateinit var mealRepository: MealRepository

    @Test
    fun getAllRestaurants() {
        var result = restaurantController.getAllRestaurants()

        assertNotNull(result)
        assertEquals(result.statusCode.value(), 200)
    }

    @Test
    fun getRestaurantByIdFailureToFind() {
        var result = restaurantController.getRestaurantById(1)
        assertNotNull(result)
        assertEquals(result.statusCode.value(), 404)


    }

    @Test
    fun getTodaysRestaurants() {
        val phone2 = Phone(null, 1, 540, 775, 2365)
        val address = Address(null, 7331, "Buchanan Dr.", "King George", "Virginia", 22485)
        val today = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val restaurantHoursToday = RestaurantHours(Time(9, 0, 0), Time(20, 0, 0), today, null)

        val restaurant = Restaurant(null, "Potato Restaurant", "", address, phone2, mutableSetOf(restaurantHoursToday), mutableSetOf())
        doReturn(listOf(restaurant)).`when`(restaurantRepository).findByDayOfWeek(today)
        var result = restaurantController.getTodaysRestaurants()
        println(result)

        assertNotNull(result)
        assertEquals(result.statusCode.value(), 200)

    }

    @Test
    fun getMealsOfRestaurant() {
        var result = restaurantController.getMealsOfRestaurant(1)
        assertNotNull(result)
        assertEquals(result.statusCode.value(), 200)

    }


}
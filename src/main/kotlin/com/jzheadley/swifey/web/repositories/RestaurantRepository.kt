package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface RestaurantRepository : JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * FROM Restaurants", nativeQuery = true)
    override fun findAll(): MutableList<Restaurant>?

    @Query(value = "Select * from Restaurants where restaurantId = ?1", nativeQuery = true)
    fun findById(restaurantId: Int): Restaurant?

    @Query(value = "SELECT DISTINCT r.* FROM " +
            "Restaurants r JOIN RestaurantHours h on r.restaurantId = h.restaurantId " +
            "WHERE h.dayOfWeek = upper( ?1 )", nativeQuery = true)
    fun findByDayOfWeek(dayOfWeek: String?): MutableList<Restaurant>?
}
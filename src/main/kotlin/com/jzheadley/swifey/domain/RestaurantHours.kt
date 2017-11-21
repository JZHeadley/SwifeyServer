package com.jzheadley.swifey.domain

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "RestaurantHours")
data class RestaurantHours(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val hoursId: Int = -1,
        var openTime: Timestamp,
        var closeTime: Timestamp,
        var dayOfWeek: String,
        @ManyToOne
        @JoinColumn(name = "restaurantId")
        var restaurant: Restaurant?
)
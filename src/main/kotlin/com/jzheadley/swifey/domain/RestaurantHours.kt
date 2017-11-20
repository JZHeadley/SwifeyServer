package com.jzheadley.swifey.domain

import java.sql.Timestamp
import java.time.Instant
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
) {
    @Suppress("unused")
    private constructor() : this(-1, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "", null)
}
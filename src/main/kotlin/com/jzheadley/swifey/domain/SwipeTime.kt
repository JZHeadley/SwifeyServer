package com.jzheadley.swifey.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "SwipeTimes")
data class SwipeTime(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val swipeTimeId: Int,
        var startTime: Time,
        var endTime: Time,
        @OneToOne
        @JsonManagedReference
        @JoinColumn(name = "restaurantId")
        var swipeRestaurant: Restaurant?)
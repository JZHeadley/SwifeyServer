package com.jzheadley.swifey.domain

import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "SwipeTimes")
data class SwipeTime(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val swipeTimeId: Int,
        var startTime: Timestamp,
        var endTime: Timestamp,
        @OneToOne
        @JoinColumn(name = "mealId")
        var meal: Meal?) {
    @Suppress("unused")
    private constructor() : this(-1, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), null)
}
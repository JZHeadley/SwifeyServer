package com.jzheadley.swifey.domain

import java.sql.Timestamp
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
        var meal: Meal?)
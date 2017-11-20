package com.jzheadley.swifey.domain

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "Orders")
data class Order(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val orderId: Int,
        var orderDate: Timestamp,
        var specialRequest: String,
        @ManyToOne
        @JoinColumn(name = "checkInId")
        var checkIn: CheckIn?,
        @OneToOne
        @JoinColumn(name = "mealId")
        var orderedMeal: Meal?,
        @OneToOne
        @JoinColumn(name = "userId")
        var user: User?
)
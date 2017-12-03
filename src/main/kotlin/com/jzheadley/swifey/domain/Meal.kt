package com.jzheadley.swifey.domain

import javax.persistence.*

@Entity
@Table(name = "Meals")
data class Meal(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val mealId: Int,
        var mealName: String,
        var mealDesc: String,
        var mealCost: Int,
        @ManyToOne
        @JoinColumn(name = "restaurantId")
        var restaurant: Restaurant?
)
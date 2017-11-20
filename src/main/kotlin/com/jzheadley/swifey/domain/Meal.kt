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
        var restaurant: Restaurant?,
        @OneToMany(mappedBy = "meal")
        var swipeTimes: Set<SwipeTime>
) {
    @Suppress("unused")
    private constructor() : this(-1, "", "", -1, null, setOf())
}
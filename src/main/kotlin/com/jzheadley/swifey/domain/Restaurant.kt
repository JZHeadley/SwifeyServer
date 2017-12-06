package com.jzheadley.swifey.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "Restaurants")
data class Restaurant(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val restaurantId: Int?,
        var restaurantName: String,
        var restaurantPhotoUrl: String,
//        var restaurantDescription: String,
        @OneToOne
        @JoinColumn(name = "addressId")
        var address: Address?,
        @OneToOne
        @JoinColumn(name = "phoneId")
        var phone: Phone?,
        @OneToMany(mappedBy = "restaurant")
        var hours: MutableSet<RestaurantHours>,
        @OneToMany(mappedBy = "swipeRestaurant")
        @JsonBackReference
        var swipeTimes: MutableSet<SwipeTime>
)

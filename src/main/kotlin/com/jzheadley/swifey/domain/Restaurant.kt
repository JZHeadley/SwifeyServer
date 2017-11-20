package com.jzheadley.swifey.domain

import javax.persistence.*

@Entity
@Table(name = "Restaurants")
data class Restaurant(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val restaurantId: Int?,
        var restaurantName: String,
        @OneToOne
        @JoinColumn(name = "addressId")
        var address: Address?,
        @OneToOne
        @JoinColumn(name = "phoneId")
        var phone: Phone?,
        @OneToMany(mappedBy = "restaurant")
        var hours: Set<RestaurantHours>) {
    @Suppress("unused")
    private constructor() : this(-1, "", null, null, setOf())
}
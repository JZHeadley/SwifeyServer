package com.jzheadley.swifey.domain

import javax.persistence.*

@Entity
@Table(name = "Addresses")
data class Address(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var addressId: Int?,
        var buildingNumber: Int,
        var streetName: String,
        var city: String,
        var state: String,
        var zipCode: Int
)
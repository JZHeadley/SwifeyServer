package com.jzheadley.swifey.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "CheckIn")
data class CheckIn(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val checkInId: Int?,
        var checkInDate: Timestamp?,
        var maxNumOrders: Int,
        var acceptingOrders: Boolean,
        @ManyToOne
        @JoinColumn(name = "userId")
//        @JsonBackReference
        val checkedInUser: User?,

        @ManyToOne
        @JoinColumn(name = "restaurantId")
        @JsonIgnoreProperties("hours", "swipeTimes")
        val restaurantCheckedInAt: Restaurant?,

        @OneToMany(mappedBy = "checkIn", targetEntity = Order::class)
        @JsonManagedReference(value = "checkInReference")
        var orders: MutableSet<Order>)
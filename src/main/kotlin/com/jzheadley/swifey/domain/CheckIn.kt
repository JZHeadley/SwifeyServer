package com.jzheadley.swifey.domain

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "CheckIn")
data class CheckIn(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val checkInId: Int?,
        var checkInDate: Timestamp,
        var maxNumOrders: Int,

        @ManyToOne
        @JoinColumn(name = "userId")
        val checkedInUser: User?,

        @ManyToOne
        @JoinColumn(name = "restaurantId")
        val restaurantCheckedInAt: Restaurant?,

        @OneToMany(mappedBy = "checkIn")
        var orders: Set<Order>)
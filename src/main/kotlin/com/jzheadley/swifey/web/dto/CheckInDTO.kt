package com.jzheadley.swifey.web.dto

import com.jzheadley.swifey.domain.Order

data class CheckInDTO(
        val checkInId: Int?,
//        var checkInDate: Timestamp,
        var maxNumOrders: Int,
        val checkedInUser: UserDTO?,
        val restaurantCheckedInAt: RestaurantDTO,
        var orders: Set<Order>)
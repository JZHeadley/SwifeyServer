package com.jzheadley.swifey.web.dto

data class CheckInDTO(
        val checkInId: Int?,
//        var checkInDate: Timestamp,
        var maxNumOrders: Int,
        val checkedInUser: UserDTO?,
        val restaurantCheckedInAt: RestaurantDTO,
        var orders: List<OrderDTO>)
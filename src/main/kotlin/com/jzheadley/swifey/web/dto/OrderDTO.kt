package com.jzheadley.swifey.web.dto

import com.jzheadley.swifey.domain.Meal


data class OrderDTO(
        val orderId: Int,
        val specialRequest: String,
//        val checkIn: CheckIn,
        val orderedMeals: List<Meal>,
        val user: UserDTO
)

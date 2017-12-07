package com.jzheadley.swifey.web.dto

import com.jzheadley.swifey.domain.Address
import com.jzheadley.swifey.domain.Phone
import com.jzheadley.swifey.domain.RestaurantHours
import com.jzheadley.swifey.domain.SwipeTime

data class RestaurantDTO(
        var restaurantId: Int?,
        var restaurantName: String,
        var restaurantPhotoUrl: String?,
//        var restaurantDescription: String,
        var address: Address?,
        var phone: Phone?,
        var hours: RestaurantHours?,
        var swipeTimes: MutableSet<SwipeTime>?
)

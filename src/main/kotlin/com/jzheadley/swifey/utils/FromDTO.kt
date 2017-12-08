package com.jzheadley.swifey.utils

import com.jzheadley.swifey.domain.CheckIn
import com.jzheadley.swifey.domain.Restaurant
import com.jzheadley.swifey.domain.User
import com.jzheadley.swifey.web.dto.CheckInDTO
import com.jzheadley.swifey.web.dto.RestaurantDTO
import com.jzheadley.swifey.web.dto.UserDTO
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

object FromDTO {
    fun checkInToDTO(checkIn: CheckIn): CheckInDTO {
        return CheckInDTO(checkIn.checkInId,
                checkIn.maxNumOrders,
                userToDTO(checkIn.checkedInUser),
                restaurantToDTO(checkIn.restaurantCheckedInAt),
                checkIn.orders)
    }

    fun userToDTO(user: User?): UserDTO {
        return UserDTO(user?.userId, user?.firstName, user?.lastName,
                user?.dob, user?.numSwipes, user?.messagingId, user?.phone)
    }

    fun restaurantToDTO(restaurant: Restaurant?): RestaurantDTO {
        val dayOfTheWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val hours = restaurant?.hours?.filter { restaurantHours -> restaurantHours.dayOfWeek.equals(dayOfTheWeek.toUpperCase()) }?.first()
        return RestaurantDTO(restaurant?.restaurantId,
                restaurant?.restaurantName,
                restaurant?.restaurantPhotoUrl,
                restaurant?.address,
                restaurant?.phone,
                hours, restaurant?.swipeTimes)
    }
}
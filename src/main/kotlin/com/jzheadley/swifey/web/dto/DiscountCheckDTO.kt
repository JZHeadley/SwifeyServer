package com.jzheadley.swifey.web.dto

import com.jzheadley.swifey.domain.Meal

data class DiscountCheckDTO(val discountCode: String, val meals: List<Meal>)
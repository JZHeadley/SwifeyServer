package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.RestaurantHours
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface RestaurantHoursRepository : CrudRepository<RestaurantHours, Long>
package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Restaurant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface RestaurantRepository : CrudRepository<Restaurant, Long>
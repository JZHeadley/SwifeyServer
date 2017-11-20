package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Meal
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface MealRepository : CrudRepository<Meal, Long>
package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.SwipeTime
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface SwipeTimeRepository : CrudRepository<SwipeTime, Long>
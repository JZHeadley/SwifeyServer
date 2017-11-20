package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.SwipeTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface SwipeTimeRepository : JpaRepository<SwipeTime, Long>
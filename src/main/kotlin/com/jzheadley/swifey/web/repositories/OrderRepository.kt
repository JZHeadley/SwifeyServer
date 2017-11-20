package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface OrderRepository : CrudRepository<Order, Long>
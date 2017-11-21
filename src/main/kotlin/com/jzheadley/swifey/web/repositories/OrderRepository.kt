package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM Orders", nativeQuery = true)
    override fun findAll(): MutableList<Order>?

    @Query(value = "Select * from Orders where orderId = ?1", nativeQuery = true)
    fun findById(orderId: Int): Order?

    @Query(value = "Select * from Orders where userId = ?1", nativeQuery = true)
    fun findByUserId(userId: String): MutableList<Order>?
}
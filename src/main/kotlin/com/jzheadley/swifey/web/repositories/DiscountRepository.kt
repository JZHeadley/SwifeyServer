package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Discount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface DiscountRepository : JpaRepository<Discount, Long> {
    @Query(value = "Select * from Discounts;", nativeQuery = true)
    override fun findAll(): MutableList<Discount>;

}
package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.CheckIn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface CheckInRepository : JpaRepository<CheckIn, Long> {
    @Query(value = "SELECT * FROM CheckIns", nativeQuery = true)
    override fun findAll(): MutableList<CheckIn>?

    @Query(value = "Select * from CheckIns where checkInId = ?1", nativeQuery = true)
    fun findById(checkInId: Int): CheckIn?

    @Query(value = "Select * from CheckIns where userId = ?1", nativeQuery = true)
    fun findByUserId(userId: String): MutableList<CheckIn>?

}

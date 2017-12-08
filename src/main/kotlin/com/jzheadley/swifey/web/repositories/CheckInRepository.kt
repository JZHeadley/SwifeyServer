package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.CheckIn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface CheckInRepository : JpaRepository<CheckIn, Long> {
    @Query(value = "SELECT * FROM CheckIn", nativeQuery = true)
    override fun findAll(): MutableList<CheckIn>?

    @Query(value = "Select * from CheckIn where checkInId = ?1", nativeQuery = true)
    fun findById(checkInId: Int): CheckIn?

    @Query(value = "SELECT * FROM checkin " +
            "WHERE userId=?1 ", nativeQuery = true)
    fun findByUserId(userId: String): MutableList<CheckIn>?

//    @Modifying
//    @Query(value = "INSERT INTO checkin (checkInDate, restaurantId, maxNumOrders, userId) VALUES (sysdate,?2,?3,?4)", nativeQuery = true)
//    @Transactional
//    fun save(restaurantId: Int, maxNumOrders: Int, userId: String): Int


}

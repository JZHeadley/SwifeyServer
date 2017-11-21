package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    override fun findAll(): MutableList<User>?

    @Query(value = "Select * from Users where userId = ?1", nativeQuery = true)
    fun findById(userId: String): User?
}
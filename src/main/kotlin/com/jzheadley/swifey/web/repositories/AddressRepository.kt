package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface AddressRepository : JpaRepository<Address, Long> {
    @Query(value = "SELECT * FROM Addresses", nativeQuery = true)
    override fun findAll(): MutableList<Address>?

    @Query(value = "Select * from Addresses where addressId = ?1", nativeQuery = true)
    fun findById(addressId: Int): Address?
}
package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Phone
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface PhoneRepository : JpaRepository<Phone, Long> {

//    @Modifying
//    @Query(value = "insert into phoneNumbers (countryCode,areaCode,exchangeNum,lineNum)  values(?1,?2,?3,?4)", nativeQuery = true)
//    fun save(countryCode: Int, areaCode: Int, exchangeNum: Int, lineNum: Int): Phone

}
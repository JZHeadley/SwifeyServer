package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Phone
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface PhoneRepository : JpaRepository<Phone, Long>
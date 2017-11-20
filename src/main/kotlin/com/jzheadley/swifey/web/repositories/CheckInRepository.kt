package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.CheckIn
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface CheckInRepository : CrudRepository<CheckIn, Long>

package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.Address
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface AddressRepository : CrudRepository<Address, Long>
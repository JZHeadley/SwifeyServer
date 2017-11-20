package com.jzheadley.swifey.web.repositories

import com.jzheadley.swifey.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@SuppressWarnings("unused")
@Repository
interface UserRepository : JpaRepository<User, Long>
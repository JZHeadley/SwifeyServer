package com.jzheadley.swifey.web.dto

import com.jzheadley.swifey.domain.Phone
import java.sql.Date

data class UserDTO(var userId: String?,
                   var firstName: String?,
                   var lastName: String?,
                   var dob: Date?,
                   var numSwipes: Int?,
                   var messagingId: String?,
                   var phone: Phone?)
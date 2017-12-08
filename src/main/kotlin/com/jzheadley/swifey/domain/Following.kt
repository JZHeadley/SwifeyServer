package com.jzheadley.swifey.domain

import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

data class Following(
        @Id
        val userId: String,
        @OneToOne
        @JoinColumn(name="followerId")
        val follower : User
)

package com.jzheadley.swifey.domain

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import java.sql.Timestamp
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "Users")
data class UserOld(
        @Id
        var userId: String = "-1",
        var firstName: String,
        var lastName: String,
        var dob: Date,
        var creationDate: Timestamp,
        var numSwipes: Int = -1,

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "phoneId")
        var phone: Phone?,

        @OneToMany(mappedBy = "checkedInUser", fetch = FetchType.LAZY)
//        @JsonBackReference
        @JsonIgnoreProperties(*arrayOf("checkedInUser"))
        var checkIns: Set<CheckIn>?,

        @ManyToMany
        @JoinTable(
                name = "Followings",
                joinColumns = arrayOf(JoinColumn(name = "userId")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "followerId"))
        )
//        @JsonIgnore
//        @JsonIgnoreProperties(value = *arrayOf("followers"))
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        val followers: Set<User>?,

        @ManyToMany(mappedBy = "followers")
        @JsonIgnore
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        val following: Set<User>?
)

package com.jzheadley.swifey.domain

import javax.persistence.*


@Entity
@Table(name = "PhoneNumbers")
data class Phone(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val phoneId: Long? = -1,
        val countryCode: Int,
        val areaCode: Int,
        val exchangeNum: Int,
        val lineNum: Int
)
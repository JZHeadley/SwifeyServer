package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.CheckIn
import com.jzheadley.swifey.domain.Restaurant
import com.jzheadley.swifey.domain.User
import com.jzheadley.swifey.web.repositories.CheckInRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.*
import org.springframework.test.context.junit4.SpringRunner
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class CheckInControllerTest {

    @InjectMocks
    lateinit var checkinController: CheckInController

    @Mock
    lateinit var checkInRepository: CheckInRepository

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun getAllCheckIns() {
//        val testUser = User()
//        val testRestaurant = Restaurant(restaurantId = 1, restaurantName = "test",)
//        var result = testRestTemplate.getForEntity("/api/checkins", String::class.java)
//        println(result)
//        assertNotNull(result)
//        assertEquals(result.statusCode, HttpStatus.OK)
//        doReturn(setOf(CheckIn(1, Date(), 1, true, testUser, ))).`when`(helloService).getHello()
//        result = helloResource.helloService()
//        assertNotNull(result)
//        assertEquals("Hello service!", result)
    }

    @Test
    fun getCheckInById() {
    }

    @Test
    fun closeCheckInForOrders() {
    }

    @Test
    fun createCheckIn() {
    }
}
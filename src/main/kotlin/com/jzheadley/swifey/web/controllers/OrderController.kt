package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.web.repositories.CheckInRepository
import com.jzheadley.swifey.web.repositories.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(val orderRepository: OrderRepository) {
    @GetMapping("/")
    fun getAllOrders() = orderRepository.findAll()
}
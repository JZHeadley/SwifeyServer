package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.Order
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.repositories.OrderRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/orders")
class OrderController(val orderRepository: OrderRepository) {
    @GetMapping("/")
    fun getAllOrders(): ResponseEntity<MutableList<Order>> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderRepository.findAll()))

    @GetMapping("/{id}")
    fun getOrderById(id: Int): ResponseEntity<Order> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderRepository.findById(id)))
}
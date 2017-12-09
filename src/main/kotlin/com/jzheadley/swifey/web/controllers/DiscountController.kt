package com.jzheadley.swifey.web.controllers

import com.jzheadley.swifey.domain.Discount
import com.jzheadley.swifey.util.ResponseUtil
import com.jzheadley.swifey.web.repositories.DiscountRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/discounts")
class DiscountController(val discountRepository: DiscountRepository) {

    @GetMapping("/")
    fun getAllDiscounts(): ResponseEntity<MutableList<Discount>> = ResponseUtil.wrapOrNotFound(Optional.ofNullable(discountRepository.findAll()))
}
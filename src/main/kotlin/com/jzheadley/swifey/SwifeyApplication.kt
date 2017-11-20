package com.jzheadley.swifey

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
class SwifeyApplication

fun main(args: Array<String>) {
    SpringApplication.run(SwifeyApplication::class.java, *args)
}


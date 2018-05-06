package com.thill.mysim.Controllers

import com.thill.mysim.Models.Airport
import com.thill.mysim.Repositories.AirportRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class Hello() {
    @Autowired
    lateinit var repo: AirportRepo
    @GetMapping("/")
    fun hello(): String {
        return "Hello!!!"
    }
    @GetMapping("/airports")
    fun airports(): List<Airport> {
        return this.repo.findAll()
    }
}
package com.thill.mysim.Controllers

import com.thill.mysim.Models.Airport
import com.thill.mysim.Repositories.AirportRepo
import com.thill.mysim.Repositories.FlightRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class Hello() {
    @Autowired
    lateinit var repo: AirportRepo
    @Autowired
    lateinit var flightRepo: FlightRepo

    @GetMapping("/airports")
    fun airports(): List<Airport> {
        return this.repo.findAll()
    }
    @GetMapping("/airports/active")
    fun activeAirports(): List<Airport> {
        val airports = this.repo.findAll()
        val flights = this.flightRepo.findAll()
        return airports.map { a ->
            a.arriving_flights =  flights
                    .filter { f -> f.arr_code == a.ident }
            a.departing_flights = flights
                    .filter { f -> f.dep_code == a.ident }
            a
        }
                .filter { a -> a.arriving_flights.isNotEmpty() || a.departing_flights.isNotEmpty()}
    }
    @GetMapping("/autocomplete/airport-idents")
    fun airportCodes(@RequestParam q: String): List<String> {
        return this.repo
                .findIdents()
                .sorted()
                .filter { a -> a.startsWith(q) }
    }
}
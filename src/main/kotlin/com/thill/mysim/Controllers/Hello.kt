package com.thill.mysim.Controllers

import com.thill.mysim.Models.Airport
import com.thill.mysim.Models.AirportDTO
import com.thill.mysim.Repositories.AirportRepo
import com.thill.mysim.Repositories.FlightRepo
import com.thill.mysim.Services.AirportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.stream.Collector
import java.util.stream.Collectors

@RestController
class Hello() {
    @Autowired
    lateinit var airportService: AirportService

    @GetMapping("/")
    fun hello(): String {
        return "Hello!!!"
    }
    @GetMapping("/airports")
    fun airports(): List<Airport> {
        return this.airportService.getAllAirports()
    }
    @GetMapping("/airportdtos")
    fun airportDTOs(): Flux<AirportDTO> {
        return this.airportService.getAllAirportsDTO()
    }


    @GetMapping("/airports/{country}/{state}")
    fun findAirportsByISO(@PathVariable country: String, @PathVariable state: String) : List<Airport> {
        return this.airportService.getAirportsByISORegion("$country-$state")
                .stream().map{a -> this.airportService.findBookedFlightCount(a)}
                .collect(Collectors.toList())
    }
}
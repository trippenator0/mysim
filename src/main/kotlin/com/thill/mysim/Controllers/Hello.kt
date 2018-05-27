package com.thill.mysim.Controllers

import com.thill.mysim.Models.Airport
import com.thill.mysim.Models.Flight
import com.thill.mysim.Models.FlightDTO
import com.thill.mysim.Models.FlightPosition
import com.thill.mysim.Repositories.AirportRepo
import com.thill.mysim.Repositories.FlightRepo
import com.thill.mysim.Services.AirportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@CrossOrigin(origins = ["http://localhost:4200", "https://fsim.thill.tech", "https://fakeflights.thill.tech"])
class Hello() {
    @Autowired
    lateinit var airportService: AirportService
    @Autowired
    lateinit var airportRepo: AirportRepo
    @Autowired
    lateinit var flightRepo: FlightRepo

    @GetMapping("/airports")
    fun airports(): List<Airport> {
        return this.airportService.getAllAirports()
    }
    @GetMapping("/airports/active")
    fun activeAirports(): List<Airport> {
        val flights = this.flightRepo.findAll().map{flight -> FlightDTO(flight.dep_code, flight.arr_code, flight.current_position) }
        var codeStream = flights.map { f -> f.dep_code} .toMutableList()
        codeStream.addAll(flights.map{ f -> f.arr_code })
        val codes = codeStream.stream().distinct().collect(Collectors.toList())
        val airports = this.airportService.getAllAirports().filter { a -> codes.contains(a.ident)}
        return airports.map { a ->
            a.arriving_flights =  flights
                    .filter { f -> f.arr_code == a.ident }
            a.departing_flights = flights
                    .filter { f -> f.dep_code == a.ident }
            a
        }
    }
    @GetMapping("/autocomplete/airport-idents")
    fun airportCodes(@RequestParam q: String): List<String> {
        return this.airportRepo
                .findIdents()
                .sorted()
                .filter { a -> a.startsWith(q) }
    }
    @GetMapping("/flights")
    fun flights(): List<FlightDTO> {
        return flightRepo.findAll().map { flight ->
            FlightDTO(flight.dep_code, flight.arr_code, flight.current_position)
        }
    }
    @PostMapping("/flights/create")
    fun addFlight(@RequestBody flight: Flight): Flight? {
        val dep =  airportRepo.findById(flight.dep_code)
        if(!dep.isPresent) {
            return null
        }
        flight.current_position = FlightPosition(lat = dep.get().lat, lng = dep.get().lng, altitude = dep.get().elevation_ft!!)
        flightRepo.save(flight)
        return flight
    }
}
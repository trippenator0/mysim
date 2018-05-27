package com.thill.mysim.Controllers

import com.thill.mysim.Models.Airport
import com.thill.mysim.Models.Flight
import com.thill.mysim.Models.FlightDTO
import com.thill.mysim.Models.FlightPosition
import com.thill.mysim.Repositories.AirportRepo
import com.thill.mysim.Repositories.FlightRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200", "https://fsim.thill.tech"])
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
        val flights = this.flightRepo.findAll().map{flight -> FlightDTO(flight.dep_code, flight.arr_code, flight.current_position) }
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
    @GetMapping("/flights")
    fun flights(): List<FlightDTO> {
        return flightRepo.findAll().map { flight ->
            FlightDTO(flight.dep_code, flight.arr_code, flight.current_position)
        }
    }
    @PostMapping("/flights/create")
    fun addFlight(@RequestBody flight: Flight): Flight? {
        val dep =  repo.findById(flight.dep_code)
        if(!dep.isPresent) {
            return null
        }
        flight.current_position = FlightPosition(lat = dep.get().lat, lng = dep.get().lng, altitude = dep.get().elevation_ft!!)
        flightRepo.save(flight)
        return flight
    }
}
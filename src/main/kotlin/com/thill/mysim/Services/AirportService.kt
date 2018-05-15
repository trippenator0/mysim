package com.thill.mysim.Services

import com.thill.mysim.Models.Airport
import com.thill.mysim.Models.AirportDTO
import com.thill.mysim.Repositories.AirportRepo
import com.thill.mysim.Repositories.FlightRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import java.util.stream.Collectors

@Service
class AirportService {
    @Autowired
    lateinit var repo: AirportRepo
    @Autowired
    lateinit var flightRepo: FlightRepo

    fun getAirportsByISORegion(iso: String) : List<Airport> {
        return this.repo.findAllByIsoRegion(iso)
    }
    fun getAllAirports() : List<Airport> {
        return this.repo.findAll()
    }

    fun getAllAirportsDTO(): Flux<AirportDTO> {
        return this.repo.findAll().stream().map { a -> AirportDTO(a.ident, a.name, a.coordinates, a.type) }
                .toFlux()
    }

    fun findBookedFlightCount(airport: Airport): Airport {
        airport.flight_count = flightRepo.findCountBookedByDepCode(airport.ident)
        return airport
    }
}
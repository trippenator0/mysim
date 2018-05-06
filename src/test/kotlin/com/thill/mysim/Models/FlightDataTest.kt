package com.thill.mysim.Models

import com.thill.mysim.Repositories.AircraftRepo
import com.thill.mysim.Repositories.AirportRepo
import com.thill.mysim.Repositories.FlightRepo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RunWith(SpringRunner::class)
class FlightDataTest {
    @Autowired
    private lateinit var flightRepo: FlightRepo
    @Autowired
    lateinit var aircraftRepo: AircraftRepo
    @Autowired
    lateinit var airportRepo: AirportRepo


    @Before
    fun setup() {
        this.airportRepo.save(Airport(ident = "KGSO", name = "PTI"))
        this.airportRepo.save(Airport(ident = "KCLT", name = "Charlotte Douglas"))
        this.aircraftRepo.save(Aircraft( name = "737", id = null))
    }

    @Test
    fun canCreateFlight() {
        val flight = Flight(aircraft_id = 1, dep_code = "KGSO", arr_code = "KCLT")
        this.flightRepo.save(flight)
        println(flight.toString())
    }
    @Test
    fun canRetrieveFlight() {
        // wtf why is id being set to 2?
        val flight = Flight(aircraft_id = 1, dep_code = "KGSO", arr_code = "KCLT")
        this.flightRepo.save(flight)
        println(this.flightRepo.findById(2))
    }
}
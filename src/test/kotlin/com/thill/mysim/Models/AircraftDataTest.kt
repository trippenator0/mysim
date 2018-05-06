package com.thill.mysim.Models

import com.thill.mysim.Repositories.AircraftRepo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RunWith(SpringRunner::class)
class AircraftDataTest {
    @Autowired
    lateinit var aircraftRepo: AircraftRepo

    @Before
    fun setup() {
        this.aircraftRepo.save(Aircraft( name = "TestName", id = null))
    }
    @Test
    fun aircraftSavesAndIsRetrievableByGeneratedID() {
        val aircraft: Optional<Aircraft> = this.aircraftRepo.findById(1)
        assert(aircraft.isPresent)
    }
}
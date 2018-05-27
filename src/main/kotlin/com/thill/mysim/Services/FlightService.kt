package com.thill.mysim.Services

import com.thill.mysim.Models.FlightPosition
import com.thill.mysim.Repositories.AirportRepo
import com.thill.mysim.Repositories.FlightRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class FlightService {
    @Autowired
    private lateinit var flightRepo: FlightRepo
    @Autowired
    private lateinit var airportRepo: AirportRepo
    val airportCodeList: List<String> = ArrayList()
    @Scheduled(initialDelay = 1000, fixedRate = 500)
    fun fly() {
        val airports = airportRepo.findAll()
        flightRepo.findAll().forEach {
            f -> run {
                    val dep = airports.first { a -> a.ident == f.dep_code }
                    val arr = airports.first { a -> a.ident == f.arr_code }
                    if (f.previous_positions.isEmpty())  {
                        val pos = FlightPosition(dep.lat, dep.lng, dep.elevation_ft!!, Timestamp.from(Instant.now()))
                        f.current_position = pos
                        f.previous_positions.add(pos)
                        flightRepo.save(f)
                        println("Saving initial flight data")
                    } else {
                        val velocityLat = if (f.current_position.lat < arr.lat) 0.01 else -0.01
                        val velocityLng = if (f.current_position.lng < arr.lng) 0.01 else -0.01
                        val pos = FlightPosition(f.current_position.lat + velocityLat, f.current_position.lng + velocityLng, dep.elevation_ft!!, Timestamp.from(Instant.now()))
                        f.current_position = pos
                        f.previous_positions.add(pos)
                        flightRepo.save(f)
                        println("Saving flight step # ${f.previous_positions.size}")

                    }
            }
        }
    }
}
package com.thill.mysim.Services


import com.thill.mysim.Models.Airport
import com.thill.mysim.Repositories.AirportRepo
import com.thill.mysim.Repositories.FlightRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service


@Service
class AirportService {
    @Autowired
    lateinit var repo: AirportRepo
    @Autowired
    lateinit var flightRepo: FlightRepo
    @Cacheable("airports")
    fun getAllAirports(): List<Airport> {
        return this.repo.findAll()
    }


}
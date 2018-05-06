package com.thill.mysim.Services

import com.thill.mysim.Repositories.FlightRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FlightService {
    @Autowired
    private lateinit var flightRepo: FlightRepo

    val airportCodeList: List<String> = ArrayList()

}
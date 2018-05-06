package com.thill.mysim.Repositories

import com.thill.mysim.Models.Flight
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightRepo: CrudRepository<Flight, Long>{
}
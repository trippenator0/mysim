package com.thill.mysim.Repositories

import com.thill.mysim.Models.Flight
import com.thill.mysim.Models.FlightStatus
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightRepo: CrudRepository<Flight, Long>{
    @Query(value = "SELECT COUNT(f.id) FROM Flight f WHERE f.dep_code = ?1 AND f.status = com.thill.mysim.Models.FlightStatus.BOOKED")
    fun findCountBookedByDepCode(dep_code: String) : Int

}
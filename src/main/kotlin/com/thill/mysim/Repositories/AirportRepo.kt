package com.thill.mysim.Repositories

import com.thill.mysim.Models.Airport
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AirportRepo: CrudRepository<Airport, String>{
     override fun findAll() : List<Airport>
    @Query("SELECT a FROM Airport a WHERE a.iso_region = ?1")
     fun findAllByIsoRegion(iso: String) : List<Airport>
}
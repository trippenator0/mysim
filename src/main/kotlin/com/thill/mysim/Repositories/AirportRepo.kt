package com.thill.mysim.Repositories

import com.thill.mysim.Models.Airport
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AirportRepo: CrudRepository<Airport, String>{
     override fun findAll() :List<Airport>
     @Query("select a.ident from Airport a")
     fun findIdents(): List<String>
}
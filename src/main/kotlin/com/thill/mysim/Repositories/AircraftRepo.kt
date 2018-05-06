package com.thill.mysim.Repositories

import com.thill.mysim.Models.Aircraft
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AircraftRepo: CrudRepository<Aircraft, Long> {
}
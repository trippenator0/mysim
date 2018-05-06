package com.thill.mysim.Repositories

import com.thill.mysim.Models.Pilot
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PilotRepo: CrudRepository<Pilot, Long> {
}
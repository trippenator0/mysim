package com.thill.mysim.Services

import com.thill.mysim.Repositories.AirportRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AirportService {
    @Autowired
    lateinit var repo: AirportRepo

}
package com.thill.mysim.Models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.thill.mysim.Repositories.FlightRepo
import lombok.NoArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity()
@Table(name = "airports")
@NoArgsConstructor
class Airport(
        @Id val ident: String = "",
        val iso_region: String = "",
        val name: String = "",
        val municipality: String = "",
        val elevation_ft: Int? = 0,
        val continent: String = "",
        val type: String = "",
        val coordinates: String = ""
        ) {

        @Transient
        var flight_count: Int = 0

}
data class AirportDTO(val ident: String = "", val name: String = "", val coordinates: String, val type: String = "")
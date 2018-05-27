package com.thill.mysim.Models

import lombok.NoArgsConstructor
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
        val lat: Double = 0.0,
        val lng: Double = 0.0,
        @Transient
        var departing_flights: List<FlightDTO> = ArrayList(),
        @Transient
        var arriving_flights: List<FlightDTO> = ArrayList()) {}
package com.thill.mysim.Models

import lombok.ToString
import java.sql.Timestamp
import java.time.Clock
import java.time.Instant
import javax.persistence.*

@Entity @ToString
data class Flight (
        @Id @GeneratedValue() val id: Long? = null,
        val aircraft_id: Long,
        val dep_code: String = "",
        val arr_code: String = "",
        val status: FlightStatus = FlightStatus.BOOKED,
        @Convert(converter = FlightPositionJSONConverter::class)
        val current_position: FlightPosition = FlightPosition(),
        @Convert(converter = FlightPositionListJSONConverter::class)
        val previous_positions: List<FlightPosition> = ArrayList(),
        val pilot: String = "") {

}
enum class FlightStatus {
    BOOKED, STARTED, CANCELLED, ENROUTE, LANDED
}
data class FlightPosition(val lat: Double = 0.0, val lng: Double = 0.0,
                          val altitude: Int = 0,
                          val timestamp: Timestamp = Timestamp.from(Instant.now(Clock.systemUTC())))
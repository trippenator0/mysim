package com.thill.mysim.Models

import com.fasterxml.jackson.databind.ObjectMapper
import javax.persistence.AttributeConverter


class FlightPositionJSONConverter : AttributeConverter<FlightPosition, String> {
    private val objectMapper: ObjectMapper = ObjectMapper()
    override fun convertToDatabaseColumn(p0: FlightPosition?): String? {
        try {
            return objectMapper.writeValueAsString(p0)
        } catch (error: Exception) {
            return null
        }
    }

    override fun convertToEntityAttribute(p0: String?): FlightPosition? {
        try {
            return objectMapper.readValue(p0, FlightPosition::class.java)
        } catch (error: Exception) {
            return null
        }
    }

}
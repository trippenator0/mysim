package com.thill.mysim.Models

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import javax.persistence.AttributeConverter


class FlightPositionListJSONConverter : AttributeConverter<List<FlightPosition>, String> {
    private val objectMapper: ObjectMapper = ObjectMapper()
    override fun convertToDatabaseColumn(p0: List<FlightPosition>?): String? {
        try {
            return objectMapper.writeValueAsString(p0)
        } catch (error: Exception) {
            return null
        }
    }

    override fun convertToEntityAttribute(p0: String?): List<FlightPosition>? {
        try {
            return objectMapper.readValue(p0!!)
        } catch (error: Exception) {
            return null
        }
    }

}
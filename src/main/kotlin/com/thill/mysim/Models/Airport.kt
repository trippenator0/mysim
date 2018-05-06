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
        val coordinates: String = ""
        ) {

}
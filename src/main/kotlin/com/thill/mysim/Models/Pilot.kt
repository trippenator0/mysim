package com.thill.mysim.Models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pilots")
data class Pilot(@Id @GeneratedValue val id: Long, val email: String){
}
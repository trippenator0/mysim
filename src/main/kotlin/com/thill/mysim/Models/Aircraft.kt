package com.thill.mysim.Models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "aircraft")
class Aircraft(@Id @GeneratedValue val id: Long?, val name: String = "") {}
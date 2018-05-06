package com.thill.mysim

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MysimApplication

fun main(args: Array<String>) {
    runApplication<MysimApplication>(*args)
}

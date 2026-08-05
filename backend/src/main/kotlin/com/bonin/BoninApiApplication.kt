package com.bonin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BoninApiApplication

fun main(args: Array<String>) {
	runApplication<BoninApiApplication>(*args)
}

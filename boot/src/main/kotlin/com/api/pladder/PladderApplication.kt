package com.api.pladder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PladderApplication

	fun main(args: Array<String>) {
		runApplication<PladderApplication>(*args)
	}
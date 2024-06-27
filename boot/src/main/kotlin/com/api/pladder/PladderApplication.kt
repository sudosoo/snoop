package com.api.pladder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class PladderApplication

	fun main(args: Array<String>) {
		runApplication<PladderApplication>(*args)
	}
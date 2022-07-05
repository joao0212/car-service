package br.com.car

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class CarServiceApplication

fun main(args: Array<String>) {
	runApplication<CarServiceApplication>(*args)
}

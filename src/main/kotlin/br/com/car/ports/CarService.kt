package br.com.car.ports

import br.com.car.core.domain.Car

interface CarService {

    fun list(model: String?): List<Car>
    fun save(car: Car): Int
    fun update(car: Car, id: Long): Int
    fun findById(id: Long): Car
    fun listByNinjaAPI(model: String): List<Car>?
}
package br.com.car.domain.ports

import br.com.car.domain.model.Car

interface CarService {

    fun list(model: String?): List<Car>
    fun save(car: Car): Int
    fun update(car: Car, id: Long): Int
    fun findById(id: Long): Car
}

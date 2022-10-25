package br.com.car.adapters.api

import br.com.car.domain.model.Car
import br.com.car.domain.ports.CarService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarResource(
    private val carService: CarService
) {

    @GetMapping
    fun list(@RequestParam(required = false) model: String?) = carService.list(model)

    @PostMapping
    fun save(@RequestBody car: Car) = carService.save(car)

    @PutMapping("/{id}")
    fun update(@RequestBody car: Car, @PathVariable id: Long) = carService.update(car, id)
}


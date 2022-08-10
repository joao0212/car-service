package br.com.car.adapters.api

import br.com.car.core.domain.Car
import br.com.car.ports.CarService
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

    @GetMapping("/ninja-api")
    fun listByNinjaAPI(@RequestParam model: String) = carService.listByNinjaAPI(model)
}

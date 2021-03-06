package br.com.car

import br.com.car.http.CarHttpService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarResource(
    private val carService: CarService,
    private val carHttpService: CarHttpService
) {

    @GetMapping
    fun list(@RequestParam(required = false) model: String?) = carService.list(model)

    @PostMapping
    fun save(@RequestBody car: Car) = carService.save(car)

    @PutMapping("/{id}")
    fun update(@RequestBody car: Car, @PathVariable id: Long) = carService.update(car, id)

    @GetMapping("/ninja-api")
    fun listByNinjaAPI(@RequestParam model: String) =
        carHttpService
            .getByModel(model)
            .execute()
            .body()
}

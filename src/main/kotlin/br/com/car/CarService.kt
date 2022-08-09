package br.com.car

import br.com.car.bd.CarRepository
import br.com.car.http.CarHttpService
import br.com.car.http.CarHttpToModelConverter
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CarService(
    private val carRepository: CarRepository,
    private val carHttpService: CarHttpService
) {

    @Cacheable(cacheNames = ["Cars"], key = "#root.method.name")
    fun list(model: String?) =
        model?.let {
            carRepository.listByModel(it)
        } ?: carRepository.listAll()

    @CacheEvict(cacheNames = ["Cars"], allEntries = true)
    fun save(car: Car) = carRepository.save(car)

    @CacheEvict(cacheNames = ["Cars"], allEntries = true)
    fun update(car: Car, id: Long) = carRepository.update(car, id)

    fun findById(id: Long): Car {
        return carRepository.findById(id) ?: throw RuntimeException()
    }

    fun listByNinjaAPI(model: String) =
        carHttpService
            .getByModel(model)
            .execute()
            .body()
            ?.let { carHttp -> CarHttpToModelConverter.toModel(carHttp) }
}

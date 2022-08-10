package br.com.car.core.service

import br.com.car.core.domain.Car
import br.com.car.adapters.http.CarHttpService
import br.com.car.ports.CarRepository
import br.com.car.core.service.converter.CarHttpToModelConverter
import br.com.car.ports.CarService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CarService(
    private val carRepository: CarRepository,
    private val carHttpService: CarHttpService
): CarService {

    @Cacheable(cacheNames = ["Cars"], key = "#root.method.name")
    override fun list(model: String?) =
        model?.let {
            carRepository.listByModel(it)
        } ?: carRepository.listAll()

    @CacheEvict(cacheNames = ["Cars"], allEntries = true)
    override fun save(car: Car) = carRepository.save(car)

    @CacheEvict(cacheNames = ["Cars"], allEntries = true)
    override fun update(car: Car, id: Long) = carRepository.update(car, id)

    override fun findById(id: Long): Car {
        return carRepository.findById(id) ?: throw RuntimeException()
    }

    override fun listByNinjaAPI(model: String) =
        carHttpService
            .getByModel(model)
            .execute()
            .body()
            ?.let { carHttp -> CarHttpToModelConverter.toModel(carHttp) }
}

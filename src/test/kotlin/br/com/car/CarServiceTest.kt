package br.com.car

import br.com.car.adapters.http.CarHttpService
import br.com.car.core.service.CarService
import br.com.car.domain.ports.CarRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.lang.RuntimeException

class CarServiceTest : FunSpec(
    {
        val car = CarFixture.getCar()
        val carHttp = CarHttpFixture.getCar()

        lateinit var carRepository: CarRepository
        lateinit var carHttpService: CarHttpService
        lateinit var carService: CarService

        beforeTest {
            carRepository = mockk {
                every { listAll() } returns listOf(car)
                every { listByModel("VW") } returns listOf(car)
            }

            carHttpService = mockk {
                coEvery { getByModel(any()) } returns listOf(carHttp)
            }

            carService = CarService(carRepository, carHttpService)
        }

        test("should return all items when carModel is null") {
            val actual = carService.list(null)

            verify(exactly = 1) { carRepository.listAll() }
            verify(exactly = 0) { carRepository.listByModel(any()) }

            actual.first().model shouldBe car.model
        }

        test("should return all items of specific model when carModel is not null") {
            val actual = carService.list("VW")

            verify(exactly = 0) { carRepository.listAll() }
            verify(exactly = 1) { carRepository.listByModel(any()) }

            actual.first().model shouldBe car.model
        }

        test("should throw a exception when not found car by id") {
            every { carRepository.findById(1) } returns null

            shouldThrow<RuntimeException> {
                carService.findById(1)
            }
        }

        test("should return one item when findById and id exists") {
            every { carRepository.findById(1) } returns car

            val actual = carService.findById(1)

            actual shouldNotBe null
        }

        test("should return a list of specific cars when getByModel and model exists") {
            val actual = carHttpService.getByModel("VW")

            actual shouldNotBe null
        }
    }
)

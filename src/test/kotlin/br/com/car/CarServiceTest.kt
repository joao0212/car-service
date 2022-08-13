package br.com.car

import br.com.car.adapters.http.CarHttpService
import br.com.car.domain.ports.CarRepository
import br.com.car.core.service.CarService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.lang.RuntimeException

class CarServiceTest : FunSpec(
    {
        val car = CarFixture.getCar()

        lateinit var carRepository: CarRepository
        lateinit var carHttpService: CarHttpService
        lateinit var carService: CarService

        beforeTest {
            carRepository =  mockk {
                every { listAll() } returns listOf(car)
                every { listByModel(any()) } returns listOf(car)
            }

            carHttpService = mockk {
                every { getByModel(any()) } returns mockk()
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
            every { carRepository.findById(any()) } returns null

            shouldThrow<RuntimeException> {
                carService.findById(1)
            }
        }

        test("should return one item when findById and id exists") {
            every { carRepository.findById(any()) } returns car

            val actual = carService.findById(1)

            actual shouldNotBe(null)
        }
    }
)
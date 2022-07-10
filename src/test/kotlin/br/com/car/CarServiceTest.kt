package br.com.car

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException

class CarServiceTest {

    private val car = Car(1, "Gol", "VW")

    private val carRepository: CarRepository = mockk {
        every { listAll() } returns listOf(car)
        every { listByModel(any()) } returns listOf(car)
    }

    private val carService: CarService = CarService(carRepository)

    @Test
    fun `should return all cars when call service without model of car`() {
        val actual = carService.list(null)

        verify(exactly = 1) { carRepository.listAll() }
        verify(exactly = 0) { carRepository.listByModel(any()) }

        assertThat(actual.first().model).isEqualTo(car.model)
    }

    @Test
    fun `should return just specific model of cars`() {
        val actual = carService.list("VW")

        verify(exactly = 0) { carRepository.listAll() }
        verify(exactly = 1) { carRepository.listByModel(any()) }

        assertThat(actual.first().model).isEqualTo(car.model)
    }

    @Test
    fun `should give a exception when not found car`() {
        every { carRepository.findById(any()) } returns null

        assertThrows<RuntimeException> {
            carService.findById(1)
        }
    }

    @Test
    fun `should return just one car`() {
        every { carRepository.findById(any()) } returns car

        val actual = carService.findById(1)

        assertThat(actual).isNotNull
    }
}
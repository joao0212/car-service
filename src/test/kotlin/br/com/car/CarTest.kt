package br.com.car

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CarTest : FunSpec({

    val car = CarFixture.getCar()

    test("should return isEligible for uber when year is greater than 2008") {
        val actual = car.isEligibleToUber()

        actual.isEligible shouldBe true
    }

    test("should not return isEligible for uber when year is less than 2008") {
        val actual = car.copy(year = 2007).isEligibleToUber()

        actual.isEligible shouldBe false
    }
})
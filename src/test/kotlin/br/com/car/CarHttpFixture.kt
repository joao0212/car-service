package br.com.car

import br.com.car.domain.http.CarHttp

object CarHttpFixture {

    fun getCar() = CarHttp(make = "VW", model = "Gol", year = 2008)
}
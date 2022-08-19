package br.com.car.core.converter

import br.com.car.domain.http.CarHttp
import br.com.car.domain.model.Car

object CarHttpToModelConverter {

    fun toModel(carsHttp: List<CarHttp>) =
        carsHttp.map { carHttp ->
            Car(
                id = Long.MAX_VALUE,
                name = carHttp.model,
                model = carHttp.make,
                year = carHttp.year
            ).isEligibleToUber()
        }
}

package br.com.car.core.service.converter

import br.com.car.core.domain.Car
import br.com.car.core.domain.http.CarHttp

object CarHttpToModelConverter {

    fun toModel(carsHttp: List<CarHttp>) =
        carsHttp.map { carHttp ->
            Car(
                id = Long.MAX_VALUE,
                name = carHttp.model,
                model = carHttp.make
            )
        }
    }



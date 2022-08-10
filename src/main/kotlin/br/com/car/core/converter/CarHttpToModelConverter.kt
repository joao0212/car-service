package br.com.car.core.converter

import br.com.car.domain.model.Car
import br.com.car.domain.http.CarHttp

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



package br.com.car.http

import br.com.car.Car

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



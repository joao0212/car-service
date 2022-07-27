package br.com.car.http

import org.springframework.web.bind.annotation.RequestParam
import retrofit2.Call
import retrofit2.http.GET

interface CarHttpService {

    @GET("/cars")
    fun getByModel(@RequestParam model: String): Call<CarHttp>
}

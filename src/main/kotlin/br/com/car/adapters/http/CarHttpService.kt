package br.com.car.adapters.http

import br.com.car.core.domain.http.CarHttp
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

@Service
interface CarHttpService {

    @GET("cars")
    fun getByModel(@Query("model") model: String): Call<List<CarHttp>>
}

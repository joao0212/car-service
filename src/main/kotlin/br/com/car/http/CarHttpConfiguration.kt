package br.com.car.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
class CarHttpConfiguration(
    @Value("\${api-ninja.key}") private val key: String
) {

    private companion object {
        const val BASE_URL = "https://api.api-ninjas.com/v1/"
    }

    private fun buildClient() = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor interceptor@ { chain ->
                val builder = chain.request().newBuilder().apply {
                    header("X-Api-Key", key)
                }.build()
                return@interceptor chain.proceed(builder)
            }
        )
    }.build()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(buildClient())
        .build()

    @Bean
    fun carHttpService(): CarHttpService = buildRetrofit().create(CarHttpService::class.java)
}